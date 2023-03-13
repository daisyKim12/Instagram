package com.project.Instargram.kotlin.src.main.page.model.post

import com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.GetSinglePostResponse


interface UserPostInterface {

    fun onGetUserPostSuccess(response: GetUserPostResponse)

    fun onGetUserPostFailure(message: String)

    fun onGetSinglePostSuccess(response: GetSinglePostResponse)

    fun onGetSinglePostFailure(message: String)

}