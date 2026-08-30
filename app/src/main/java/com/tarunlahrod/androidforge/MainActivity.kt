package com.tarunlahrod.androidforge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.tarunlahrod.androidforge.auth.SessionState
import com.tarunlahrod.androidforge.databinding.ActivityMainBinding
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
                    navigateFor(state)
                }
            }
        }
    }

    private fun navigateFor(state: SessionState) {
        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHost.navController

        when (state) {
            SessionState.Restoring -> {
                // RestoringFragment is already the start destination
            }

            SessionState.LoggedOut -> {
                navController.navigate(
                    R.id.auth_graph,
                    null,
                    navOptions {
                        launchSingleTop = true
                        popUpTo(R.id.nav_graph) {
                            inclusive = false
                        }
                    }
                )
            }

            SessionState.LoggedIn -> {
                navController.navigate(
                    R.id.main_graph,
                    null,
                    navOptions {
                        launchSingleTop = true
                        popUpTo(R.id.nav_graph) {
                            inclusive = false
                        }
                    }
                )
            }
        }
    }
}