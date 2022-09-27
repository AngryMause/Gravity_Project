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
            writeApiModelToShared()
            checkIsFirstSignIn()
        }
    }

    private fun checkIsFirstSignIn() {
        val isFirstSignIn = sharedPreferenceService.readFromSharedIsSign()
        if (isFirstSignIn) {
            _response.value = showWebViewFragmentWithHomeUrl()
        } else {
            sharedPreferenceService.writeToShareIsSignIn(true)
            _response.value = showWebViewFragmentWithLinkUrl()
        }
    }

    private fun showWebViewFragmentWithHomeUrl(): String {
        val model = sharedPreferenceService.readResponse()
        return model.home
    }

    private fun showWebViewFragmentWithLinkUrl(): String {
        val model = sharedPreferenceService.readResponse()
        return model.link
    }

    private suspend fun writeApiModelToShared() {
        sharedPreferenceService.writeResponse(apiRepository.getResponse()
            .body()!!)
    }

}