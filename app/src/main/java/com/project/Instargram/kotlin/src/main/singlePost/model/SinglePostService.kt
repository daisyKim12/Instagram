package com.project.Instargram.kotlin.src.main.singlePost.model

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.main.MainRetrofitInterface
import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SinglePostService(val singlePostInterface: SinglePostInterface) {

    fun tryNewFollow(newFollowRequest: NewFollowRequest){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.postNewFollow(newFollowRequest).enqueue(object : Callback<NewFollowResponse?> {
            override fun onResponse(call: Call<NewFollowResponse?>, response: Response<NewFollowResponse?>) {
                singlePostInterface.onNewFollowSuccess(response.body() as NewFollowResponse)
            }

            override fun onFailure(call: Call<NewFollowResponse?>, t: Throwable) {
                singlePostInterface.onNewFollowFailure("통신 오류")

            }
        })
    }

    fun tryUnFollow(newFollowRequest: NewFollowRequest) {
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.postUnFollow(newFollowRequest).enqueue(object : Callback<NewFollowResponse?> {
            override fun onResponse(call: Call<NewFollowResponse?>, response: Response<NewFollowResponse?>) {
                singlePostInterface.onUnFollowSuccess(response.body() as NewFollowResponse)
            }

            override fun onFailure(call: Call<NewFollowResponse?>, t: Throwable) {
                singlePostInterface.onUnFollowFailure("통신 오류")
            }
        })
    }
}