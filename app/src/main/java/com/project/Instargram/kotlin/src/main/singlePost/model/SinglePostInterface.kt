package com.project.Instargram.kotlin.src.main.singlePost.model

import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowResponse

interface SinglePostInterface {

    fun onNewFollowSuccess(response: NewFollowResponse)

    fun onNewFollowFailure(message: String)

    fun onUnFollowSuccess(response: NewFollowResponse)

    fun onUnFollowFailure(message: String)

}