package com.example.gravity_project.localdata

import android.content.SharedPreferences
import com.example.gravity_project.model.ApiResponseModel
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

private const val RESPONSE = "api_response"
private const val IS_FIRST_SIGN_IN = "is_first_sig_in"

@Singleton
class SharedPreferenceService @Inject constructor(private val sharedPreferences: SharedPreferences) {
    private var myShared: SharedPreferences = sharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private val gso = Gson()

    fun writeToShareIsSignIn(value: Boolean) {
        editor = myShared.edit()
        editor.putBoolean(IS_FIRST_SIGN_IN, value)
        editor.apply()
    }

    fun writeResponse(value: ApiResponseModel) {
        val str = gso.toJsonTree(value)
        editor = myShared.edit()
        editor.putString(RESPONSE, str.toString())
        editor.apply()
    }

    fun readFromSharedIsSign(): Boolean {
        myShared = sharedPreferences
        return myShared.getBoolean(IS_FIRST_SIGN_IN, false)
    }

    fun readApiResponse(): ApiResponseModel {
        myShared = sharedPreferences
        val json = myShared.getString(RESPONSE, "empty")
        val data = gso.fromJson(json, ApiResponseModel::class.java)
        return ApiResponseModel(link = data.link, home = data.home)
    }
}