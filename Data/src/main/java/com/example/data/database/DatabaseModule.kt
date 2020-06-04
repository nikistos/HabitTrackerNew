package com.example.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideDatabase(): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "habitDatabase"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDao(database: AppDataBase): HabitDao {
        return database.habitDao()
    }
}