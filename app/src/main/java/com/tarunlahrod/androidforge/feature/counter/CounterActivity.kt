package com.tarunlahrod.androidforge.feature.counter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.tarunlahrod.androidforge.databinding.ActivityCounterBinding
import kotlinx.coroutines.launch

class CounterActivity : AppCompatActivity() {

    private val viewModel: CounterViewModel by viewModels()
    private lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupClickListeners()
        observeUiState()
    }

    private fun setupBinding() {
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        }
    }

    private fun render(uiState: CounterUiState) {
        binding.tvCount.text = uiState.count.toString()
    }
}