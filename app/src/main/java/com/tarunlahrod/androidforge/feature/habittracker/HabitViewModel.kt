package com.tarunlahrod.androidforge.feature.habittracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HabitViewModel(
    private val repository: HabitRepository
) : ViewModel() {

    val habits: StateFlow<List<Habit>> =
        repository.observeHabits()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )


    fun addHabit(name: String) {
        viewModelScope.launch {
            repository.addHabit(name)
        }
    }

    fun toggleHabit(id: Long) {
        viewModelScope.launch {
            repository.toggleHabit(id)
        }
    }
}