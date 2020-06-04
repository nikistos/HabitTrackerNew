package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.*
import androidx.room.Room
import javax.inject.Inject


@Database(entities = [HabitEntity::class], version = 2)
abstract class AppDataBase : RoomDatabase() {
    abstract fun habitDao(): HabitDao

//    companion object {
//        private var INSTANCE: AppDataBase? = null
//
//        fun getInstance(): AppDataBase {
//            var instance = INSTANCE
//            if (instance == null) {
//                instance = Room.databaseBuilder(
//                    context,
//                    AppDataBase::class.java,
//                    "habitDatabase"
//                )
//                    .allowMainThreadQueries()
//                    .build()
//            }
//            INSTANCE = instance
//            return instance
//        }
//    }
}