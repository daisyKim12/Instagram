package com.project.Instargram.kotlin.src.main.page.model.profile

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.main.MainRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PageService(val profileInterface: ProfileInterface) {

    fun tryGetProfile(userIdx: Int){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.getProfile(userIdx).enqueue(object : Callback<GetProfileResponse?> {
            override fun onResponse(call: Call<GetProfileResponse?>, response: Response<GetProfileResponse?>) {
                profileInterface.onGetProfileSuccess(response.body() as GetProfileResponse)
            }

            override fun onFailure(call: Call<GetProfileResponse?>, t: Throwable) {
                profileInterface.onGetProfileFailure("통신 오류")
            }
        })
    }
}