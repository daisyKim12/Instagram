package com.project.Instargram.kotlin.src.login.model.PhoneAuth


interface PhoneActivityInterface {

    fun onPostPhoneDuplicationSuccess(response: PhoneDuplicateResponse)

    fun onPostPhoneDuplicationFailure(message: String)
}