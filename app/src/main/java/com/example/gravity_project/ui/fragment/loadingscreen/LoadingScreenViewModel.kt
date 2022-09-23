package com.example.gravity_project.ui.fragment.loadingscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gravity_project.localdata.SharedPreferenceService
import com.example.gravity_project.model.ResponseModel
import com.example.gravity_project.network.retrofit.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
            delay(1000)
            checkIsFirstSignIn()
            delay(1000)
            writeModelToShared()
        }
    }

    private suspend fun getResponse(): ResponseModel {
        val data = apiRepository.getResponse().body()
        return data.let {
            ResponseModel.mapData(it!!)
        }
    }


    private suspend fun checkIsFirstSignIn() {
        val isFirstSignIn = sharedPreferenceService.readFromSharedIsSign()
        if (isFirstSignIn) {
            _response.value = showWebViewFragmentWithHomeUrl()
        } else {
            sharedPreferenceService.writeToShareIsSignIn(true)
            delay(500)
            _response.value = showWebViewFragmentWithLinkUrl()
        }
    }


    private suspend fun showWebViewFragmentWithHomeUrl(): String {
        delay(5000)
        val model = sharedPreferenceService.readResponse()
        return model.home
    }

    private suspend fun showWebViewFragmentWithLinkUrl(): String {
        delay(5000)
        return getResponse().link
    }

    private suspend fun writeModelToShared() {
        sharedPreferenceService.writeResponse(ResponseModel.mapData(apiRepository.getResponse()
            .body()!!))
    }

}