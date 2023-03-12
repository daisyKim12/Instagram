package com.project.Instargram.kotlin.src.main.post.model

import com.project.Instargram.kotlin.src.login.model.newaccount.NewAccountResponse

interface NewPostInterface {

    fun onPostNewPostSuccess(response: NewPostResponse)
    fun onPostNewPostFailure(message: String)
}