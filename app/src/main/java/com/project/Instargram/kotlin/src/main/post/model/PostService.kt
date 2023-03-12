package com.project.Instargram.kotlin.src.main.post.model

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.login.model.newaccount.NewAccountResponse
import com.project.Instargram.kotlin.src.main.MainRetrofitInterface
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostService(val newPostInterface: NewPostInterface) {

    fun tryPostNewPost(data: MultipartBody.Part, image: MultipartBody.Part){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.postNewPost(data, image).enqueue(object : Callback<NewPostResponse?> {
            override fun onResponse(call: Call<NewPostResponse?>, response: Response<NewPostResponse?>) {
                newPostInterface.onPostNewPostSuccess(response.body() as NewPostResponse)
            }

            override fun onFailure(call: Call<NewPostResponse?>, t: Throwable) {
                newPostInterface.onPostNewPostFailure("통신 오류")
            }
        })
    }
}