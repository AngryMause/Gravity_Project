package com.example.gravity_project.ui.fragment.loadingscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gravity_project.localdata.SharedPreferenceService
import com.example.gravity_project.network.retrofit.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingScreenViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val sharedPreferenceService: SharedPreferenceService,
) : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String> get() = _response

    init {
        viewModelScope.launch(Dispatchers.Main) {
            writeApiModelResponseToShared()
            checkIsFirstSignIn()
        }
    }

    private fun checkIsFirstSignIn() {
        val isFirstSignIn = sharedPreferenceService.readFromSharedIsSign()
        if (!isFirstSignIn) {
            sharedPreferenceService.writeToShareIsSignIn(true)
            _response.value = sendWebViewFragmentWithLinkUrl()
        } else {
            _response.value = sendWebViewFragmentWithHomeUrl()
        }
    }

    private fun sendWebViewFragmentWithHomeUrl(): String {
        val model = sharedPreferenceService.readApiResponse()
        return model.home
    }

    private fun sendWebViewFragmentWithLinkUrl(): String {
        val model = sharedPreferenceService.readApiResponse()
        return model.link
    }

    private suspend fun writeApiModelResponseToShared() {
        val isFirstSignIn = sharedPreferenceService.readFromSharedIsSign()
        if (!isFirstSignIn) {
            sharedPreferenceService.writeResponse(apiRepository.getResponse().body()!!)
        }

    }
}