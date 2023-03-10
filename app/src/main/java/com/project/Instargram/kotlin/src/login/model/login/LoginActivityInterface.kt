package com.project.Instargram.kotlin.src.login.model.login


interface LoginActivityInterface {

    fun onPostEmailLoginSuccess(response: LoginResponse)
    fun onPostPhoneLoginSuccess(response: LoginResponse)
    fun onPostLoginFailure(message: String)
}