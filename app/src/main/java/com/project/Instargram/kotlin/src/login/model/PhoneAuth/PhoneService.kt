package com.project.Instargram.kotlin.src.login.model.PhoneAuth

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailDuplicateRequest
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailDuplicateResponse
import com.project.Instargram.kotlin.src.login.model.LoginRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneService(val phoneActivityInterface: PhoneActivityInterface) {

    fun tryPostPhoneDuplication(phoneDuplicateRequest: PhoneDuplicateRequest) {
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postPhoneDuplication(phoneDuplicateRequest).enqueue(object : Callback<PhoneDuplicateResponse?> {
            override fun onResponse(call: Call<PhoneDuplicateResponse?>, response: Response<PhoneDuplicateResponse?>) {
                phoneActivityInterface.onPostPhoneDuplicationSuccess(response.body() as PhoneDuplicateResponse)
            }

            override fun onFailure(call: Call<PhoneDuplicateResponse?>, t: Throwable) {
                phoneActivityInterface.onPostPhoneDuplicationFailure("통신 오류")
            }
        })

    }
}