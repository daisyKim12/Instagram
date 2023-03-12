package com.project.Instargram.kotlin.src.main.home.models.no

import com.project.Instargram.kotlin.src.main.home.models.no.PostSignUpRequest
import com.project.Instargram.kotlin.src.main.home.models.no.SignUpResponse
import com.project.Instargram.kotlin.src.main.home.models.no.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface HomeRetrofitInterface {
    @GET("/template/users")
    fun getUsers() : Call<UserResponse>

    @POST("/template/users")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>
}
