package com.project.Instargram.kotlin.src.main.home

import com.google.gson.annotations.SerializedName
import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.main.MainRetrofitInterface
import com.project.Instargram.kotlin.src.main.home.models.getFeed.GetPostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val homeInterface: HomeInterface) {

    fun tryGetFeed(userIdx: Int, pageNum: Int){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.getFeed(userIdx, pageNum).enqueue(object : Callback<GetPostResponse?> {
            override fun onResponse(call: Call<GetPostResponse?>, response: Response<GetPostResponse?>) {
                homeInterface.onGetFeedSuccess(response.body() as GetPostResponse)
            }

            override fun onFailure(call: Call<GetPostResponse?>, t: Throwable) {
                homeInterface.onGetFeedFailure("통신 오류")
            }
        })
    }
}