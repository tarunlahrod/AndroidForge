package com.tarunlahrod.androidforge.feature.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.tarunlahrod.androidforge.AndroidForgeApplication
import com.tarunlahrod.androidforge.databinding.FragmentCounterBinding
import kotlinx.coroutines.launch

class CounterFragment : Fragment() {
    private var _binding: FragmentCounterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CounterViewModel by viewModels()
    private val app: AndroidForgeApplication
        get() = requireActivity().application as AndroidForgeApplication

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCounterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeUiState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    render(state)
                }
            }
        }
    }

    private fun setupClickListeners() {
        binding.apply {
            btnReset.setOnClickListener {
                viewModel.reset()
            }

            btnDec.setOnClickListener {
                viewModel.decrement()
            }

            btnInc.setOnClickListener {
                viewModel.increment()
            }

            btnLogout.setOnClickListener {
                lifecycleScope.launch {
                    app.appContainer.authSession.logout()
                }
            }
        }
    }

    private fun render(uiState: CounterUiState) {
        binding.tvCount.text = uiState.count.toString()
    }
}