package com.example.level4_task_1.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Api {
    companion object {
        const val CATS_BASE_URL = "https://cataas.com/"
        const val DOGS_BASE_URL = "https://dog.ceo/"

        // the lazy keyword makes sure the createApi function is not called until these properties are accessed
        val catsClient by lazy { createApi(CATS_BASE_URL) }
        val dogsClient by lazy { createApi(DOGS_BASE_URL) }

        fun createApi(baseUrl: String): ApiService {
            val client = OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                readTimeout(10, TimeUnit.SECONDS)
                writeTimeout(10, TimeUnit.SECONDS)
            }.build()

            // Create the Retrofit instance
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}