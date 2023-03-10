package com.project.Instargram.kotlin.src.login.model.newaccount

interface NewAccountInterface {

    fun onPostNewAccountSuccess(response: NewAccountResponse)
    fun onPostNewAccountFailure(message: String)
}