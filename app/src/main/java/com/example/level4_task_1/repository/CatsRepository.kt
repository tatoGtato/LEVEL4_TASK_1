package com.example.level4_task_1.repository

import android.util.Log
import com.example.level4_task_1.data.api.Api
import com.example.level4_task_1.data.api.Api.Companion.CATS_BASE_URL
import com.example.level4_task_1.data.api.Api.Companion.catsClient
import com.example.level4_task_1.data.api.ApiService
import com.example.level4_task_1.data.api.util.Resource
import com.example.level4_task_1.data.model.Cat
import kotlinx.coroutines.withTimeout

class CatsRepository {
    private val apiService: ApiService = Api.createApi(CATS_BASE_URL)

    suspend fun getRandomCat() : Resource<Cat> {

        val response = try {
            withTimeout(5_000) {
                apiService.getRandomCat()
            }
        } catch(e: Exception) {
            Log.e("NumbersRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occurred")
        }

        return Resource.Success(response)
    }
}