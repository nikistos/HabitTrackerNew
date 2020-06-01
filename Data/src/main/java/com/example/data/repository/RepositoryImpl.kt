package com.example.data.repository

import com.example.data.apiservice.DoubletService
import com.example.data.database.HabitDao
import com.example.data.mappers.HabitMapper
import com.example.domain.models.DomainHabit
import com.example.domain.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val database: HabitDao,
    private val token: String,
    private val apiService: DoubletService,
    private val mapper: HabitMapper
) : Repository {

    override suspend fun refreshDatabase() {
        withContext(Dispatchers.IO) {
            val habitsFromNetworkContainer = apiService.getHabits(token)
            database.insertHabitsList(habitsFromNetworkContainer.map {
                mapper.networkToEntityModel(it)
            })
        }
    }

    override suspend fun addHabit(habit: DomainHabit) {
        withContext(Dispatchers.IO) {
            val habitUid = apiService.insertHabit(token, mapper.domainToNetworkModel(habit))
            habit.apply { uid = habitUid.uid }
            database.insert(mapper.domainToEntity(habit))
        }
    }

    override suspend fun updateHabit(habit: DomainHabit) {
        withContext(Dispatchers.IO) {
            var habitUid = apiService.updateHabit(token, mapper.domainToNetworkModel(habit))
            database.update(mapper.domainToEntity(habit))
        }
    }

    override fun getHabitById(id: String): DomainHabit {
        val habitEntity = database.getHabitById(id)
        return mapper.entityToDomainModel(habitEntity)
    }

    override fun getHabitList(): Flow<List<DomainHabit>> {
        return database.getAllHabits()
            .map { list -> list.map { entity -> mapper.entityToDomainModel(entity) } }
    }
}