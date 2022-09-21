package com.example.gravity_project.ui.fragment.loadingscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gravity_project.model.ResponseModel
import com.example.gravity_project.network.retrofit.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadingScreenViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
) : ViewModel() {
    private val _response = MutableLiveData<ResponseModel>()
    val response: LiveData<ResponseModel> get() = _response


    init {
        viewModelScope.launch(Dispatchers.Main) {
            _response.value = getResponse()
        }
    }


    private suspend fun getResponse(): ResponseModel {
        val data = apiRepository.getResponse().body()
        return ResponseModel(home = data!!.home, link = data!!.link)
    }


}