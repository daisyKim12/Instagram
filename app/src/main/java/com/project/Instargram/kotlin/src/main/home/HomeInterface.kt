package com.project.Instargram.kotlin.src.main.home

import com.project.Instargram.kotlin.src.main.home.models.getFeed.GetPostResponse

interface HomeInterface {

    fun onGetFeedSuccess(response: GetPostResponse)

    fun onGetFeedFailure(message: String)
}