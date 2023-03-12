package com.project.Instargram.kotlin.src.main

import com.project.Instargram.kotlin.src.login.model.newaccount.NewAccountResponse
import com.project.Instargram.kotlin.src.main.post.model.NewPostResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MainRetrofitInterface {

    @Multipart
    @POST("posts")
    fun postNewPost(@Part data: MultipartBody.Part, @Part image: MultipartBody.Part)
            : Call<NewPostResponse>
}