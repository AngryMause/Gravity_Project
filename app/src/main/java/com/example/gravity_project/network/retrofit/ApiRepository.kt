package com.example.gravity_project.network.retrofit

import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getResponse() = apiService.getResponse()

}