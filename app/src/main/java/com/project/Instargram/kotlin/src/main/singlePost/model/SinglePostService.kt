package com.project.Instargram.kotlin.src.main.singlePost.model

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.main.MainRetrofitInterface
import com.project.Instargram.kotlin.src.main.singlePost.model.bookmark.NewBookmarkRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.bookmark.NewBookmarkResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.like.NewLikeRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.like.NewLikeResponse
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
        mainRetrofitInterface.patchUnFollow(newFollowRequest).enqueue(object : Callback<NewFollowResponse?> {
            override fun onResponse(call: Call<NewFollowResponse?>, response: Response<NewFollowResponse?>) {
                singlePostInterface.onUnFollowSuccess(response.body() as NewFollowResponse)
            }

            override fun onFailure(call: Call<NewFollowResponse?>, t: Throwable) {
                singlePostInterface.onUnFollowFailure("통신 오류")
            }
        })
    }

    fun tryNewLike(newLikeRequest: NewLikeRequest){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.postNewLike(newLikeRequest).enqueue(object : Callback<NewLikeResponse?> {
            override fun onResponse(
                call: Call<NewLikeResponse?>,
                response: Response<NewLikeResponse?>
            ) {
                singlePostInterface.onNewLikeSuccess(response.body() as NewLikeResponse)
            }

            override fun onFailure(call: Call<NewLikeResponse?>, t: Throwable) {
                singlePostInterface.onNewLikeFailure("통신 오류")
            }
        })
    }

    fun tryUnLike(newLikeRequest: NewLikeRequest) {
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.patchUnLike(newLikeRequest).enqueue(object : Callback<NewLikeResponse?> {
            override fun onResponse(
                call: Call<NewLikeResponse?>,
                response: Response<NewLikeResponse?>
            ) {
                singlePostInterface.onUnLikeSuccess(response.body() as NewLikeResponse)
            }

            override fun onFailure(call: Call<NewLikeResponse?>, t: Throwable) {
                singlePostInterface.onUnLikeFailure("통신 오류")
            }
        })
    }


    fun tryNewBookmark(newBookmarkRequest: NewBookmarkRequest){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.postNewBookmark(newBookmarkRequest).enqueue(object : Callback<NewBookmarkResponse?> {
            override fun onResponse(
                call: Call<NewBookmarkResponse?>,
                response: Response<NewBookmarkResponse?>
            ) {
                singlePostInterface.onNewBookmarkSuccess(response.body() as NewBookmarkResponse)
            }

            override fun onFailure(call: Call<NewBookmarkResponse?>, t: Throwable) {
                singlePostInterface.onNewBookmarkFailure("통신 오류")
            }
        })
    }

    fun tryUnBookmark(newBookmarkRequest: NewBookmarkRequest) {
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.patchUnBookmark(newBookmarkRequest).enqueue(object : Callback<NewBookmarkResponse?> {
            override fun onResponse(
                call: Call<NewBookmarkResponse?>,
                response: Response<NewBookmarkResponse?>
            ) {
                singlePostInterface.onUnBookmarkSuccess(response.body() as NewBookmarkResponse)
            }

            override fun onFailure(call: Call<NewBookmarkResponse?>, t: Throwable) {
                singlePostInterface.onUnBookmarkFailure("통신 오류")
            }
        })
    }



}