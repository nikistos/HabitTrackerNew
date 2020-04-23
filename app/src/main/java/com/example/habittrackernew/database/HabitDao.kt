package com.example.habittrackernew.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface HabitDao {
    @Insert
    fun insert(habit: HabitEntity)

    @Update
    fun update(habit: HabitEntity)

    @Query("SELECT * FROM habits WHERE uid = :key")
    fun getHabitById(key: Int): LiveData<HabitEntity>

    @Query("SELECT * FROM habits")
    fun getAllHabits(): LiveData<List<HabitEntity>>
}