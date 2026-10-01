package com.tarunlahrod.androidforge.feature.habittracker.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits")
    fun observeHabits(): Flow<List<HabitEntity>>

    @Insert
    suspend fun insertHabit(habit: HabitEntity)

    @Query("UPDATE habits SET isCompleted = NOT isCompleted WHERE id = :id")
    suspend fun toggleHabit(id: Long)
}