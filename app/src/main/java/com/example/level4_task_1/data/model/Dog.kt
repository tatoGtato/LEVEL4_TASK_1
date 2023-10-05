package com.example.level4_task_1.data.model

import com.google.gson.annotations.SerializedName

data class Dog(
    @SerializedName("message") val randomDogPictureUrl: String,
    @SerializedName("status") val result: String
)
