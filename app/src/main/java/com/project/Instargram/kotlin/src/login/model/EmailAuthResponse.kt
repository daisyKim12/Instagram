package com.project.Instargram.kotlin.src.login.model

import com.project.Instargram.kotlin.config.BaseResponse

data class EmailAuthResponse(
    val result: Result
): BaseResponse()
