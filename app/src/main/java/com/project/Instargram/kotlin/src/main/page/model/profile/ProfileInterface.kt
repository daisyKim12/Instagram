package com.project.Instargram.kotlin.src.main.page.model.profile

import com.project.Instargram.kotlin.src.main.page.model.profile.GetProfileResponse

interface ProfileInterface {

    fun onGetProfileSuccess(response: GetProfileResponse)

    fun onGetProfileFailure(message: String)
}