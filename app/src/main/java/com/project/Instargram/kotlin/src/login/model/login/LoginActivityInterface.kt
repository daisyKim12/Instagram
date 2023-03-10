package com.project.Instargram.kotlin.src.login.model.login


interface LoginActivityInterface {

    fun onPostEmailLoginSuccess(response: EmailLoginResponse)

    fun onPostEmailLoginFailure(message: String)
}