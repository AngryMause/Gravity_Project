package com.example.gravity_project.network.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("prod")
    suspend fun getResponse(): Response<ApiModel>
}