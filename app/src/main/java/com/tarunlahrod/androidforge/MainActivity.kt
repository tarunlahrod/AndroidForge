package com.tarunlahrod.androidforge

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.tarunlahrod.androidforge.auth.SessionState
import com.tarunlahrod.androidforge.databinding.ActivityMainBinding
import com.tarunlahrod.androidforge.feature.counter.CounterActivity
import com.tarunlahrod.androidforge.feature.login.LoginActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val app: AndroidForgeApplication
        get() = application as AndroidForgeApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()

        observeSession()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun observeSession() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                app.appContainer.authSession.state.collect { state ->
                    when (state) {
                        SessionState.Restoring -> Unit

                        SessionState.LoggedIn -> {
                            startActivity(
                                Intent(this@MainActivity, CounterActivity::class.java)
                            )
                            finish()
                        }

                        SessionState.LoggedOut -> {
                            startActivity(
                                Intent(this@MainActivity, LoginActivity::class.java)
                            )
                            finish()
                        }
                    }
                }
            }
        }
    }
}