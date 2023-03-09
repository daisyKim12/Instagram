package com.project.Instargram.kotlin.src.login

import com.project.Instargram.kotlin.src.login.model.EmailAuthResponse

interface LoginActivityInterface {

    fun onPostEmailAuthSuccess(response: EmailAuthResponse)

    fun onPostEmailAuthFailure(message: String)
}