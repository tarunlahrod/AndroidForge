package com.tarunlahrod.androidforge.feature.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.tarunlahrod.androidforge.AndroidForgeApplication
import com.tarunlahrod.androidforge.auth.SessionState
import com.tarunlahrod.androidforge.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val app: AndroidForgeApplication
        get() = requireActivity().application as AndroidForgeApplication

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            repository = app.appContainer.authRepository,
            authSession = app.appContainer.authSession
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeUiState()
        observeUiEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupClickListeners() {
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

    private fun observeSessionState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                app.appContainer.authSession.state.collect { state ->
                    when (state) {
                        SessionState.Restoring -> {
                            // Do nothing
                        }

                        SessionState.LoggedIn -> {

                        }

                        SessionState.LoggedOut -> {
                            // do nothing
                        }
                    }
                }
            }
        }
    }

    private fun observeUiEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect { event ->
                    when (event) {
                        is LoginUiEvent.ShowToast -> {
                            Toast.makeText(
                                requireContext(),
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