package com.tarunlahrod.androidforge.feature.habittracker

data class Habit(
    val id: Long,
    val name: String,
    val isCompleted: Boolean = false
)
