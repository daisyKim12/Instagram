package com.project.Instargram.kotlin.src.login.model.EmailAuth

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.login.LoginRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmailService(val emailActivityInterface: EmailActivityInterface) {

    fun tryPostEmailAuth(emailAuthRequest: EmailAuthRequest){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postEmailAuth(emailAuthRequest).enqueue(object :
            Callback<EmailAuthResponse?> {
            override fun onResponse(call: Call<EmailAuthResponse?>, response: Response<EmailAuthResponse?>) {
                emailActivityInterface.onPostEmailAuthSuccess(response.body() as EmailAuthResponse)
            }

            override fun onFailure(call: Call<EmailAuthResponse?>, t: Throwable) {
                emailActivityInterface.onPostEmailAuthFailure("통신 오류")
            }
        })
    }

    fun tryPostEmailDuplication(emailDuplicateRequest: EmailDuplicateRequest) {
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postEmailDuplication(emailDuplicateRequest).enqueue(object :
            Callback<EmailDuplicateResponse?> {
            override fun onResponse(
                call: Call<EmailDuplicateResponse?>,
                response: Response<EmailDuplicateResponse?>
            ) {
                emailActivityInterface.onPostEmailDuplicationSuccess(response.body() as EmailDuplicateResponse)
            }

            override fun onFailure(call: Call<EmailDuplicateResponse?>, t: Throwable) {
                emailActivityInterface.onPostEmailDuplicationFailure("통신오류")
            }
        })

    }
}