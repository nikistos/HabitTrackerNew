package com.example.habittrackernew.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [HabitEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun habitDao(): HabitDao

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(applicationContext: Context): AppDataBase {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                    applicationContext,
                    AppDataBase::class.java,
                    "habitDatabase"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            INSTANCE = instance
            return instance
        }
    }
}