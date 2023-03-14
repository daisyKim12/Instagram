package com.project.Instargram.kotlin.src.main.follow.model

import com.project.Instargram.kotlin.src.main.follow.model.follower.GetFollowerResponse
import com.project.Instargram.kotlin.src.main.follow.model.following.GetFollowingResponse

interface FollowInterface {

    fun onGetFollowerSuccess(response: GetFollowerResponse)

    fun onGetFollowerFailure(message: String)

    fun onGetFollowingSuccess(response: GetFollowingResponse)

    fun onGetFollowingFailure(message: String)
}