package com.example.domain.usecases

import com.example.domain.models.DomainHabit
import com.example.domain.models.HabitDone
import com.example.domain.repositories.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HabitProcessingService @Inject constructor(private val repository: Repository) {

    fun getHabitById(id: String): DomainHabit = repository.getHabitById(id)

    fun getHabitList(): Flow<List<DomainHabit>> = repository.getHabitList()

    suspend fun refreshDatabase() = repository.refreshDatabase()

    suspend fun addHabit(habit: DomainHabit) = repository.addHabit(habit)

    suspend fun updateHabit(habit: DomainHabit) = repository.updateHabit(habit)

    suspend fun processHabitDone(habitDone: HabitDone) = repository.processHabitDone(habitDone)

}