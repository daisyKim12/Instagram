package com.project.Instargram.kotlin.src.login.model.newaccount

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.login.model.LoginRetrofitInterface
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewAccountService(val newAccountInterface: NewAccountInterface) {

    fun tryPostEmailNewAccount(data: MultipartBody.Part, image: MultipartBody.Part){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postEmailNewAccount(data, image).enqueue(object :
            Callback<NewAccountResponse?> {
            override fun onResponse(call: Call<NewAccountResponse?>, response: Response<NewAccountResponse?>) {
                newAccountInterface.onPostNewAccountSuccess(response.body() as NewAccountResponse)
            }

            override fun onFailure(call: Call<NewAccountResponse?>, t: Throwable) {
                newAccountInterface.onPostNewAccountFailure("통신 오류")
            }
        })
    }
    fun tryPostPhoneNewAccount(data: MultipartBody.Part, image: MultipartBody.Part){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postPhoneNewAccount(data, image).enqueue(object :
            Callback<NewAccountResponse?> {
            override fun onResponse(call: Call<NewAccountResponse?>, response: Response<NewAccountResponse?>) {
                newAccountInterface.onPostNewAccountSuccess(response.body() as NewAccountResponse)
            }

            override fun onFailure(call: Call<NewAccountResponse?>, t: Throwable) {
                newAccountInterface.onPostNewAccountFailure("통신 오류")
            }
        })
    }
}