package com.tarunlahrod.androidforge.feature.login

import com.google.gson.Gson
import com.tarunlahrod.androidforge.network.ApiErrorType
import com.tarunlahrod.androidforge.network.ApiResult
import com.tarunlahrod.androidforge.network.NetworkClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runTest

class AuthRepositoryTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var repository: AuthRepository

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val networkClient = NetworkClient(
            gson = Gson(),
            baseUrl = mockWebServer.url("/").toString()
        )

        val authApi = networkClient.createApi(AuthApi::class.java)
        repository = AuthRepository(authApi)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `login returns success when server responds with 200`() = runTest {
        // Arrange
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    """
                        {
                            "success": true
                        }
                    """.trimIndent()
                )
        )

        // Act
        val result = repository.login(
            email = "admin@test.com",
            password = "1234"
        )

        // Assert
        assertTrue(result is ApiResult.Success)
    }

    @Test
    fun `login returns failure when server responds with 401`() = runTest {
        // Arrange
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(401)
                .setBody(
                    """
                        {
                            "code": "INVALID_CREDENTIALS",
                            "message": "Invalid credentials"
                        }
                    """.trimIndent()
                )
        )

        // Act
        val result = repository.login(
            email = "admin@test.com",
            password = "wrong"
        )

        // Assert
        assertTrue(result is ApiResult.Failure)

        val error = (result as ApiResult.Failure).error

        assertEquals(401, error.httpCode)
        assertEquals("INVALID_CREDENTIALS", error.code)
        assertEquals("Invalid credentials", error.message)
    }

    @Test
    fun `login returns server failure when server responds with 500`() = runTest {
        // Arrange
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
                .setBody(
                    """
                        {
                            "code": "INTERNAL_SERVER_ERROR",
                            "message": "Something went wrong on the server"
                        }
                    """.trimIndent()
                )
        )

        // Act
        val result = repository.login(
            email = "admin@test.com",
            password = "1234"
        )

        // Assert
        assertTrue(result is ApiResult.Failure)

        val error = (result as ApiResult.Failure).error

        assertEquals(500, error.httpCode)
        assertEquals("INTERNAL_SERVER_ERROR", error.code)
        assertEquals("Something went wrong on the server", error.message)
    }

    @Test
    fun `login returns network failure when server is unreachable`() = runTest {
        // Arrange - for this test, we're going to shut the server down before making the request
        mockWebServer.shutdown()

        // Act
        val result = repository.login(
            email = "admin@test.com",
            password = "1234"
        )

        // Assert
        assertTrue(result is ApiResult.Failure)

        val error = (result as ApiResult.Failure).error

        assertEquals(ApiErrorType.Network, error.type)
    }

    @Test
    fun `login returns failure when server error body is malformed`() = runTest {
        // Arrange
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
                .setBody("this is not a valid JSON")
        )

        // Act
        val result = repository.login(
            email = "admin@test.com",
            password = "1234"
        )

        // Assert
        assertTrue(result is ApiResult.Failure)
    }
}