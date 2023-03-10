package com.project.Instargram.kotlin.src.login.model.login

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.login.model.LoginRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val loginActivityInterface: LoginActivityInterface) {

    fun tryPostEmailLogin(emailLoginRequest: EmailLoginRequest){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postEmailLogin(emailLoginRequest).enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(call: Call<LoginResponse?>, response: Response<LoginResponse?>) {
                loginActivityInterface.onPostEmailLoginSuccess(response.body() as LoginResponse)
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                loginActivityInterface.onPostLoginFailure("통신 실패")
            }
        })
    }

    fun tryPostPhoneLogin(phoneLoginRequest: PhoneLoginRequest){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postPhoneLogin(phoneLoginRequest).enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(call: Call<LoginResponse?>, response: Response<LoginResponse?>) {
                loginActivityInterface.onPostEmailLoginSuccess(response.body() as LoginResponse)
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                loginActivityInterface.onPostLoginFailure("통신 실패")
            }
        })
    }

}