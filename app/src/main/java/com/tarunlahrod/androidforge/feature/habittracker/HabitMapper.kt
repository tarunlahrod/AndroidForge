package com.tarunlahrod.androidforge.feature.habittracker

import com.tarunlahrod.androidforge.feature.habittracker.db.HabitEntity

fun HabitEntity.toDomain(): Habit {
    return Habit(
        id = id,
        name = name,
        isCompleted = isCompleted
    )
}

fun Habit.toEntity(): HabitEntity {
    return HabitEntity(
        id = id,
        name = name,
        isCompleted = isCompleted
    )
}