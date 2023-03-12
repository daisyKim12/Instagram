package com.project.Instargram.kotlin.src.main.home.models.no

import com.google.gson.annotations.SerializedName
import com.project.Instargram.kotlin.config.BaseResponse
import com.project.Instargram.kotlin.src.main.home.models.no.ResultSignUp

data class SignUpResponse(
        // 베이스 리스폰스를 상속 받았으므로, 아래 내용은 포함이 되었습니다.
        // @SerializedName("isSuccess") val isSuccess: Boolean,
        // @SerializedName("code") val code: Int,
        // @SerializedName("message") val message: String,
        @SerializedName("result") val result: ResultSignUp
) : BaseResponse()