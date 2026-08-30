package com.tarunlahrod.androidforge.auth

import com.tarunlahrod.androidforge.network.InMemoryTokenProvider
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class AuthSessionTest {

    @Test
    fun `initial state is restoring`() = runTest {
        // Arrange
        val tokenProvider = InMemoryTokenProvider()
        val authSession = AuthSession(tokenProvider)

        // Assert
        assertEquals(SessionState.Restoring, authSession.state.value)
    }

    @Test
    fun `restore with existing token results in logged in state`() = runTest {
        // Arrange
        val tokenProvider = InMemoryTokenProvider()
        tokenProvider.saveAccessToken("abc123")

        val authSession = AuthSession(tokenProvider)

        // Act
        authSession.restore()

        // Assert
        assertEquals(SessionState.LoggedIn, authSession.state.value)
    }

    @Test
    fun `restore without token results in logged out state`() = runTest {
        // Arrange
        val tokenProvider = InMemoryTokenProvider()
        val authSession = AuthSession(tokenProvider)

        // Act
        authSession.restore()

        // Assert
        assertEquals(SessionState.LoggedOut, authSession.state.value)
    }

    @Test
    fun `logout clears token and results in logged out state`() = runTest {
        // Arrange
        val tokenProvider = InMemoryTokenProvider()
        tokenProvider.saveAccessToken("abc123")

        val authSession = AuthSession(tokenProvider)
        authSession.restore()
        assertEquals(SessionState.LoggedIn, authSession.state.value)

        // Act
        authSession.logout()

        // Assert
        assertEquals(SessionState.LoggedOut, authSession.state.value)
        assertEquals(null, tokenProvider.getAccessToken())
    }

    @Test
    fun `logout when already logged out remains logged out`() = runTest {
        // Arrange
        val tokenProvider = InMemoryTokenProvider()
        val authSession = AuthSession(tokenProvider)

        // Act
        authSession.logout()

        // Assert
        assertEquals(SessionState.LoggedOut, authSession.state.value)
        assertEquals(null, tokenProvider.getAccessToken())
    }
}