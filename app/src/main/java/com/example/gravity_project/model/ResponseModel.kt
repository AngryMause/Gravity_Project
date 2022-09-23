package com.example.gravity_project.model

import com.example.gravity_project.network.retrofit.ApiModel

data class ResponseModel(
    val home: String,
    val link: String,
) {
    companion object {
        fun mapData(apiModel: ApiModel): ResponseModel =
            ResponseModel(home = apiModel.home, link = apiModel.link)
    }
}
