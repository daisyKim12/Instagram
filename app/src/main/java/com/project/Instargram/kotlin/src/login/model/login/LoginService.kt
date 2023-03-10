package com.project.Instargram.kotlin.src.login.model.login

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.login.model.LoginRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val loginActivityInterface: LoginActivityInterface) {

    fun tryPostEmailLogin(emailLoginRequest: EmailLoginRequest){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postEmailLogin(emailLoginRequest).enqueue(object : Callback<EmailLoginResponse?> {
            override fun onResponse(call: Call<EmailLoginResponse?>, response: Response<EmailLoginResponse?>) {
                loginActivityInterface.onPostEmailLoginSuccess(response.body() as EmailLoginResponse)
            }

            override fun onFailure(call: Call<EmailLoginResponse?>, t: Throwable) {
                loginActivityInterface.onPostEmailLoginFailure("통신 실패")
            }
        })
    }

}