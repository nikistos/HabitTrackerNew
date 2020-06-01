package com.example.data.repository

import com.example.data.apiservice.NetworkModule
import com.example.data.database.DatabaseModule
import com.example.domain.repositories.Repository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class RepositoryModule {

    @Provides
    fun provideRepository(repositoryImpl: RepositoryImpl): Repository {
        return repositoryImpl
    }
}