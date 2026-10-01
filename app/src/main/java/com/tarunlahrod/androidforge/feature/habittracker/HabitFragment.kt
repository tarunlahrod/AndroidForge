package com.tarunlahrod.androidforge.feature.habittracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.tarunlahrod.androidforge.AndroidForgeApplication
import com.tarunlahrod.androidforge.databinding.FragmentHabitBinding
import com.tarunlahrod.androidforge.feature.habittracker.worker.HabitStatsWorker
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class HabitFragment : Fragment() {
    private var _binding: FragmentHabitBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HabitViewModel by viewModels()
    private val app: AndroidForgeApplication
        get() = requireActivity().application as AndroidForgeApplication

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInsets()
        setupClickListeners()
        observeUiState()
        setUpWorker()
        setUpWorkerObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                view.paddingLeft, systemBars.top, view.paddingRight, systemBars.bottom
            )

            insets
        }
    }

    private fun setupClickListeners() {

    }

    private fun observeUiState() {

    }

    private fun setUpWorkerObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                WorkManager.getInstance(requireContext())
                    .getWorkInfosForUniqueWorkFlow("habit_stats_test").collect { workInfos ->
                        val workInfo = workInfos.firstOrNull()
                    }
            }
        }
    }

    private fun setUpWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

//        val workRequest =
//            PeriodicWorkRequestBuilder<HabitStatsWorker>(
//                repeatInterval = 15,
//                repeatIntervalTimeUnit = TimeUnit.MINUTES
//            ).setConstraints(constraints).setBackoffCriteria(
//                backoffPolicy = BackoffPolicy.EXPONENTIAL,
//                backoffDelay = 10,
//                timeUnit = TimeUnit.SECONDS
//            ).build()


//        WorkManager.getInstance(requireContext()).enqueueUniquePeriodicWork(
//            uniqueWorkName = "habit_stats",
//            existingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP,
//            request = workRequest
//        )

        val workRequest = OneTimeWorkRequestBuilder<HabitStatsWorker>()
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.LINEAR,
                backoffDelay = 5,
                timeUnit = TimeUnit.SECONDS
            )
            .build()

        WorkManager
            .getInstance(requireContext())
            .enqueueUniqueWork(
                "habit_stats_test",
                ExistingWorkPolicy.REPLACE,
                workRequest
            )
    }
}