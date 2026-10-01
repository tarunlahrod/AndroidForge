package com.tarunlahrod.androidforge.feature.habittracker

import com.tarunlahrod.androidforge.feature.habittracker.db.HabitDao
import com.tarunlahrod.androidforge.feature.habittracker.db.HabitEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HabitRepository(
    private val dao: HabitDao
) {
    fun observeHabits(): Flow<List<Habit>> {
        return dao.observeHabits().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun addHabit(name: String) {
        dao.insertHabit(
            HabitEntity(name = name)
        )
    }

    suspend fun toggleHabit(id: Long) {
        dao.toggleHabit(id)
    }
}