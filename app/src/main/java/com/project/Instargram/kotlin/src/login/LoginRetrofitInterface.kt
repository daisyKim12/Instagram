package com.project.Instargram.kotlin.src.login

import com.project.Instargram.kotlin.src.login.model.*
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailAuthRequest
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailAuthResponse
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailDuplicateRequest
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailDuplicateResponse
import com.project.Instargram.kotlin.src.login.model.login.EmailLoginRequest
import com.project.Instargram.kotlin.src.login.model.login.EmailLoginResponse
import com.project.Instargram.kotlin.src.login.model.newaccount.NewAccountResponse
import com.project.Instargram.kotlin.src.main.home.models.PostSignUpRequest
import com.project.Instargram.kotlin.src.main.home.models.SignUpResponse
import com.project.Instargram.kotlin.src.main.home.models.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface LoginRetrofitInterface {

    @GET("/template/users")
    fun getUsers() : Call<UserResponse>

    @POST("/template/users")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>

    @POST("auths/email-auth")
    fun postEmailAuth(@Body emailAuthRequest: EmailAuthRequest)
    : Call<EmailAuthResponse>

    @POST("auths/duplicate-check/email")
    fun postEmailDuplication(@Body emailDuplicateRequest: EmailDuplicateRequest)
            : Call<EmailDuplicateResponse>

    @POST("auths/duplicate-check/email")
    fun postEmailLogin(@Body emailLoginRequest: EmailLoginRequest)
            : Call<EmailLoginResponse>

    @Multipart
    @POST("auths/sign-up/email")
    fun postNewAccount(@Part("sign-up-data") data: RequestBody, @Part image: MultipartBody.Part)
    : Call<NewAccountResponse>
}