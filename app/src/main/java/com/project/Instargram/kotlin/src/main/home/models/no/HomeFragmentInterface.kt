package com.project.Instargram.kotlin.src.main.home.models.no

import com.project.Instargram.kotlin.src.main.home.models.no.SignUpResponse
import com.project.Instargram.kotlin.src.main.home.models.no.UserResponse

interface HomeFragmentInterface {

    fun onGetUserSuccess(response: UserResponse)

    fun onGetUserFailure(message: String)

    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)
}