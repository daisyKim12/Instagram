package com.project.Instargram.kotlin.src.main.home.models.no

import com.google.gson.annotations.SerializedName

data class ResultSignUp(
        @SerializedName("userId") val userId: Int,
        @SerializedName("jwt") val jwt: String
)