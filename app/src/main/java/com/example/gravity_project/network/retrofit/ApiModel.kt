package com.example.gravity_project.network.retrofit

import com.google.gson.annotations.SerializedName

data class ApiModel(
    @SerializedName("link")
    val link: String,
    @SerializedName("home")
    val home: String,
)