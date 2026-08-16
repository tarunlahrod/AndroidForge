package com.tarunlahrod.androidforge.feature.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.tarunlahrod.androidforge.AndroidForgeApplication
import com.tarunlahrod.androidforge.databinding.ActivityLoginBinding
import com.tarunlahrod.androidforge.feature.counter.CounterActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val app: AndroidForgeApplication
        get() = application as AndroidForgeApplication

    val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(repository = app.appContainer.authRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setClickListeners()
        observeUiState()
        observeUiEvents()
    }

    private fun setupBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setClickListeners() {
        binding.apply {
            etEmail.doAfterTextChanged {
                viewModel.onEmailChanged(it.toString())
            }

            etPassword.doAfterTextChanged {
                viewModel.onPasswordChanged(it.toString())
            }

            btnLogin.setOnClickListener {
                viewModel.login()
            }
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    render(uiState)
                }
            }
        }
    }

    private fun observeUiEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect { event ->
                    when (event) {
                        LoginUiEvent.NavigateToHome -> {
                            startActivity(
                                Intent(this@LoginActivity, CounterActivity::class.java)
                            )
                        }

                        is LoginUiEvent.ShowToast -> {
                            Toast.makeText(
                                this@LoginActivity,
                                event.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun render(uiState: LoginUiState) {
        binding.apply {
            btnLogin.isEnabled = uiState.isLoginEnabled
            progressCircular.isVisible = uiState.isLoading
        }
    }
}