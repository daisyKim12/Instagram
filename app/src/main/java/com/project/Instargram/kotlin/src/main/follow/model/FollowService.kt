package com.project.Instargram.kotlin.src.main.follow.model

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.main.MainRetrofitInterface
import com.project.Instargram.kotlin.src.main.follow.model.follower.GetFollowerResponse
import com.project.Instargram.kotlin.src.main.follow.model.following.GetFollowingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class FollowService(val followInterface: FollowInterface) {

    fun tryGetFollower(requestUserIdx: Int,targetUserIdx: Int){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.getFollower(requestUserIdx, targetUserIdx).enqueue(object : Callback<GetFollowerResponse?> {
            override fun onResponse(
                call: Call<GetFollowerResponse?>,
                response: Response<GetFollowerResponse?>
            ) {
                followInterface.onGetFollowerSuccess(response.body() as GetFollowerResponse)
            }

            override fun onFailure(call: Call<GetFollowerResponse?>, t: Throwable) {
                followInterface.onGetFollowerFailure("통신 오류")
            }
        })
    }

    fun tryGetFollowing(requestUserIdx: Int,targetUserIdx: Int){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.getFollowing(requestUserIdx, targetUserIdx).enqueue(object : Callback<GetFollowingResponse?> {
            override fun onResponse(
                call: Call<GetFollowingResponse?>,
                response: Response<GetFollowingResponse?>
            ) {
                followInterface.onGetFollowingSuccess(response.body() as GetFollowingResponse)
            }

            override fun onFailure(call: Call<GetFollowingResponse?>, t: Throwable) {
                followInterface.onGetFollowingFailure("통신 오류")
            }
        })
    }
}