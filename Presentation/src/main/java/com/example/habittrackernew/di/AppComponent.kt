package com.example.habittrackernew.di

import com.example.data.apiservice.NetworkModule
import com.example.data.database.DatabaseModule
import com.example.data.repository.RepositoryModule
import com.example.domain.repositories.Repository
import com.example.domain.usecases.HabitProcessingService
import com.example.habittrackernew.list_of_habits.ListViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, RepositoryModule::class])
interface AppComponent {
    fun getHabitProcessingService(): HabitProcessingService

}