package com.project.Instargram.kotlin.src.login

import com.project.Instargram.kotlin.src.login.model.EmailAuthRequest
import com.project.Instargram.kotlin.src.login.model.EmailAuthResponse
import com.project.Instargram.kotlin.src.main.home.models.PostSignUpRequest
import com.project.Instargram.kotlin.src.main.home.models.SignUpResponse
import com.project.Instargram.kotlin.src.main.home.models.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginRetrofitInterface {

    @GET("/template/users")
    fun getUsers() : Call<UserResponse>

    @POST("/template/users")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>

    @POST("auths/email-auth")
    fun postEmailAuth(@Body emailAuthRequest: EmailAuthRequest): Call<EmailAuthResponse>
}