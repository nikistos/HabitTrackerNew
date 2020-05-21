package com.example.habittrackernew.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*


@Dao
interface HabitDao {
    @Insert
    fun insert(habit: HabitEntity)

    @Update
    fun update(habit: HabitEntity)

    @Query("SELECT * FROM habits WHERE uid = :key")
    fun getHabitById(key: String): HabitEntity

    @Query("SELECT * FROM habits")
    fun getAllHabits(): LiveData<List<HabitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabitsList(habits: List<HabitEntity>)
}