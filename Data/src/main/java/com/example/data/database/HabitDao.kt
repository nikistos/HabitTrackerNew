package com.example.data.database


import androidx.room.*
import com.example.data.models.*
import kotlinx.coroutines.flow.Flow


@Dao
interface HabitDao {
    @Insert
    fun insert(habit: HabitEntity)

    @Update
    fun update(habit: HabitEntity)

    @Query("SELECT * FROM habits WHERE uid = :key")
    fun getHabitById(key: String): HabitEntity

    @Query("SELECT * FROM habits")
    fun getAllHabits(): Flow<List<HabitEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabitsList(habits: List<HabitEntity>)
}