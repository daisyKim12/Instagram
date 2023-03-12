package com.project.Instargram.kotlin.src.main.page.model

import com.project.Instargram.kotlin.src.main.home.models.getFeed.GetPostResponse

interface ProfileInterface {

    fun onGetProfileSuccess(response: GetProfileResponse)

    fun onGetProfileFailure(message: String)
}