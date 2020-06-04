package com.example.data.apiservice

import com.example.data.models.*
import com.example.domain.models.HabitDone
import retrofit2.http.*

interface DoubletService {

    @GET("api/habit")
    suspend fun getHabits(@Header("Authorization") authorization: String): List<NetworkHabit>

    @PUT("api/habit")
    suspend fun updateHabit(
        @Header("Authorization") authorization: String,
        @Body habit: NetworkHabit
    ): HabitUid

    @PUT("api/habit")
    suspend fun insertHabit(
        @Header("Authorization") authorization: String,
        @Body habit: NetworkHabit
    ): HabitUid

    @POST("api/habit_done")
    suspend fun processHabitDone( @Header("Authorization") authorization: String, @Body habitDone: HabitDone)
}