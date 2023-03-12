package com.project.Instargram.kotlin.src.main.page.model.post

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.main.MainRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPostService(val userPostInterface: UserPostInterface) {

    fun tryGetUserPost(userIdx: Int){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.getUserPost(userIdx).enqueue(object : Callback<GetUserPostResponse?> {
            override fun onResponse(call: Call<GetUserPostResponse?>, response: Response<GetUserPostResponse?>) {
                userPostInterface.onGetUserPostSuccess(response.body() as GetUserPostResponse)
            }

            override fun onFailure(call: Call<GetUserPostResponse?>, t: Throwable) {
                userPostInterface.onGetUserPostFailure("통신 오류")
            }
        })
    }
}