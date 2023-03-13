package com.project.Instargram.kotlin.src.main.singlePost.model

import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.like.NewLikeResponse

interface SinglePostInterface {

    fun onNewFollowSuccess(response: NewFollowResponse)

    fun onNewFollowFailure(message: String)

    fun onUnFollowSuccess(response: NewFollowResponse)

    fun onUnFollowFailure(message: String)


    fun onNewLikeSuccess(response: NewLikeResponse)

    fun onNewLikeFailure(message: String)

    fun onUnLikeSuccess(response: NewLikeResponse)

    fun onUnLikeFailure(message: String)



}