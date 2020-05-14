package com.example.habittrackernew.repository

import androidx.lifecycle.LiveData
import com.example.habittrackernew.database.HabitDao
import com.example.habittrackernew.database.HabitEntity
import com.example.habittrackernew.network.DoubletNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class HabitRepository(private val database: HabitDao, private val token: String) {

    private val doubletApi = DoubletNetwork.service

    suspend fun refreshDatabase() {
        withContext(Dispatchers.IO) {

            try {
                var habitsFromNetworkContainer = doubletApi.getHabits(token)
                database.insertHabitsList(habitsFromNetworkContainer.map {
                    it.asLocalModel()
                })
            } catch (e: Exception) {

            }
        }
    }

    suspend fun addHabit(habit: HabitEntity) {
        withContext(Dispatchers.IO) {
            try {
                var habitUid = doubletApi.insertHabit(token, habit.asNetworkModel())
                database.insert(habit.apply { uid = habitUid.uid })
            } catch (e: Exception) {

            }
        }
    }

    suspend fun updateHabit(habit: HabitEntity) {
        withContext(Dispatchers.IO) {
            try {
                var habidUid = doubletApi.updateHabit(token, habit.asNetworkModel())
                database.update(habit)
            } catch (e: Exception) {

            }
        }
    }
}