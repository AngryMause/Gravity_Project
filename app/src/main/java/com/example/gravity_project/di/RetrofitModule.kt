package com.example.gravity_project.di

import com.example.gravity_project.network.retrofit.ApiRepository
import com.example.gravity_project.network.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://efs5i1ube5.execute-api.eu-central-1.amazonaws.com/"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun providesBaseUrl(): String = BASE_URL

    @Provides
    @Singleton
    fun providesOkHTTPLoggingInterceptor(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    }

    @Provides
    @Singleton
    fun providesRetrofit(BASE_URL: String, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun apiRepository(apiService: ApiService): ApiRepository =
        ApiRepository(apiService)
}