package com.example.domain.repositories

import com.example.domain.models.DomainHabit
import com.example.domain.models.HabitDone
import kotlinx.coroutines.flow.Flow


interface Repository {
    fun getHabitById(id: String): DomainHabit

    fun getHabitList(): Flow<List<DomainHabit>>

    suspend fun refreshDatabase()

    suspend fun addHabit(habit: DomainHabit)

    suspend fun updateHabit(habit: DomainHabit)

    suspend fun processHabitDone(habitDone: HabitDone)
}