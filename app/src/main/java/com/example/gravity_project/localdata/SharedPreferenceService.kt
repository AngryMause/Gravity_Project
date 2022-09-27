package com.example.gravity_project.localdata

import android.content.SharedPreferences
import com.example.gravity_project.network.retrofit.ApiModel
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

private const val RESPONSE = "model"
private const val IS_FIRST_SIGN_IN = "isFirst"

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

    fun writeResponse(value: ApiModel) {
        val str = gso.toJsonTree(value)
        editor = myShared.edit()
        editor.putString(RESPONSE, str.toString())
        editor.apply()
    }

    fun readFromSharedIsSign(): Boolean {
        myShared = sharedPreferences
        return myShared.getBoolean(IS_FIRST_SIGN_IN, false)
    }

    fun readResponse(): ApiModel {
        myShared = sharedPreferences
        val json = myShared.getString(RESPONSE, "empty")
        val data = gso.fromJson(json, ApiModel::class.java)
        return ApiModel(data.home, data.link)
    }
}