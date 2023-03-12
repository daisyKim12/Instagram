package com.project.Instargram.kotlin.src.login.model

import com.project.Instargram.kotlin.src.login.model.*
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailAuthRequest
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailAuthResponse
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailDuplicateRequest
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailDuplicateResponse
import com.project.Instargram.kotlin.src.login.model.PhoneAuth.PhoneDuplicateRequest
import com.project.Instargram.kotlin.src.login.model.PhoneAuth.PhoneDuplicateResponse
import com.project.Instargram.kotlin.src.login.model.login.EmailLoginRequest
import com.project.Instargram.kotlin.src.login.model.login.LoginResponse
import com.project.Instargram.kotlin.src.login.model.login.PhoneLoginRequest
import com.project.Instargram.kotlin.src.login.model.newaccount.NewAccountResponse
import com.project.Instargram.kotlin.src.main.home.models.no.PostSignUpRequest
import com.project.Instargram.kotlin.src.main.home.models.no.SignUpResponse
import com.project.Instargram.kotlin.src.main.home.models.no.UserResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface LoginRetrofitInterface {
    //example
    @GET("/template/users")
    fun getUsers() : Call<UserResponse>
    @POST("/template/users")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>

    //Email
    @POST("auths/email-auth")
    fun postEmailAuth(@Body emailAuthRequest: EmailAuthRequest)
    : Call<EmailAuthResponse>

    @POST("auths/duplicate-check/email")
    fun postEmailDuplication(@Body emailDuplicateRequest: EmailDuplicateRequest)
            : Call<EmailDuplicateResponse>

    @POST("auths/sign-in/email")
    fun postEmailLogin(@Body emailLoginRequest: EmailLoginRequest)
            : Call<LoginResponse>
    @Multipart
    @POST("auths/sign-up/email")
    fun postEmailNewAccount(@Part data: MultipartBody.Part, @Part image: MultipartBody.Part)
    : Call<NewAccountResponse>


    //Phone
    @POST("auths/duplicate-check/phone")
    fun postPhoneDuplication(@Body phoneDuplicateRequest: PhoneDuplicateRequest)
            : Call<PhoneDuplicateResponse>
    @POST("auths/sign-in/phone")
    fun postPhoneLogin(@Body phoneLoginRequest: PhoneLoginRequest)
            : Call<LoginResponse>
    @Multipart
    @POST("auths/sign-up/phone")
    fun postPhoneNewAccount(@Part data: MultipartBody.Part, @Part image: MultipartBody.Part)
            : Call<NewAccountResponse>

}
