package com.example.level4_task_1.repository

import android.util.Log
import com.example.level4_task_1.data.api.Api
import com.example.level4_task_1.data.api.ApiService
import com.example.level4_task_1.data.api.util.Resource
import com.example.level4_task_1.data.model.Cat
import com.example.level4_task_1.data.model.Dog
import kotlinx.coroutines.withTimeout

class DogsRepository {
    private val apiService: ApiService = Api.createApi(Api.DOGS_BASE_URL)

    suspend fun getRandomDog() : Resource<Dog> {

        val response = try {
            withTimeout(5_000) {
                apiService.getRandomDog()
            }
        } catch(e: Exception) {
            Log.e("NumbersRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occurred")
        }

        return Resource.Success(response)
    }
}