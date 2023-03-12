package com.project.Instargram.kotlin.src.main.page.model.post


interface UserPostInterface {

    fun onGetUserPostSuccess(response: GetUserPostResponse)

    fun onGetUserPostFailure(message: String)
}