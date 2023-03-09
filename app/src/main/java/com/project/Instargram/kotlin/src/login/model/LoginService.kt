package com.project.Instargram.kotlin.src.login.model

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.login.LoginActivityInterface
import com.project.Instargram.kotlin.src.login.LoginRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val loginActivityInterface: LoginActivityInterface) {

    fun tryPostEmailAuth(emailAuthRequest: EmailAuthRequest){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postEmailAuth(emailAuthRequest).enqueue(object : Callback<EmailAuthResponse?> {
            override fun onResponse(call: Call<EmailAuthResponse?>, response: Response<EmailAuthResponse?>) {
                loginActivityInterface.onPostEmailAuthSuccess(response.body() as EmailAuthResponse)
            }

            override fun onFailure(call: Call<EmailAuthResponse?>, t: Throwable) {
                loginActivityInterface.onPostEmailAuthFailure("통신 오류")
            }
        })
    }
}