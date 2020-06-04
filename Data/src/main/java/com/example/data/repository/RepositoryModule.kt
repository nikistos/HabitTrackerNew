package com.example.data.repository

import com.example.data.apiservice.DoubletService
import com.example.data.apiservice.NetworkModule
import com.example.data.database.DatabaseModule
import com.example.data.database.HabitDao
import com.example.data.mappers.HabitMapper
import com.example.domain.repositories.Repository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class RepositoryModule(private val token: String) {

    @Provides
    fun provideRepository(repositoryImpl: RepositoryImpl): Repository {
        return repositoryImpl
    }

    @Provides
    fun provideRepositoryImpl(
        habitDao: HabitDao,
        doubletService: DoubletService,
        mapper: HabitMapper
    ): RepositoryImpl {
        return RepositoryImpl(habitDao, token, doubletService, mapper)
    }
}