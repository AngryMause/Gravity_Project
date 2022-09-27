package com.example.gravity_project.network.retrofit

import com.example.gravity_project.model.ApiResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("prod")
    suspend fun getResponse(): Response<ApiResponseModel>
}