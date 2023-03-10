package com.project.Instargram.kotlin.src.login.model.EmailAuth

interface EmailActivityInterface {

    fun onPostEmailAuthSuccess(response: EmailAuthResponse)

    fun onPostEmailAuthFailure(message: String)

    fun onPostEmailDuplicationSuccess(response: EmailDuplicateResponse)

    fun onPostEmailDuplicationFailure(message: String)
}