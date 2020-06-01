package com.example.habittrackernew.di

import com.example.data.apiservice.DoubletService
import com.example.data.apiservice.NetworkModule
import com.example.data.database.DatabaseModule
import com.example.data.database.HabitDao
import com.example.data.mappers.HabitMapper
import com.example.data.repository.RepositoryImpl
import com.example.data.repository.RepositoryModule
import com.example.domain.repositories.Repository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, DatabaseModule::class, RepositoryModule::class])
class AppModule(
    private val token: String
) {
    @Provides
    fun provideRepository(
        habitDao: HabitDao,
        doubletService: DoubletService,
        mapper: HabitMapper
    ): RepositoryImpl {
        return RepositoryImpl(habitDao, token, doubletService, mapper)
    }
}