package com.example.habittrackernew.di

import android.app.Application
import com.example.data.database.DatabaseModule
import com.example.data.repository.RepositoryModule
import com.example.habittrackernew.R

class HabitTrackerApplication : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(this))
            .appModule(AppModule(resources.getString(R.string.authToken)))
            .build()
    }
}