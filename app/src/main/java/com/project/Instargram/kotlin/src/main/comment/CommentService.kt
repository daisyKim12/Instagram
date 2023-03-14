package com.project.Instargram.kotlin.src.main.comment

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.main.MainRetrofitInterface
import com.project.Instargram.kotlin.src.main.comment.model.getComment.GetCommentResponse
import com.project.Instargram.kotlin.src.main.comment.model.newComment.NewCommentRequest
import com.project.Instargram.kotlin.src.main.comment.model.newComment.NewCommentResponse
import com.project.Instargram.kotlin.src.main.comment.model.newReply.NewReplyRequest
import com.project.Instargram.kotlin.src.main.comment.model.newReply.NewReplyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentService(val commentInterface: CommentInterface) {

    fun tryNewComment(newCommentRequest: NewCommentRequest){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.postComment(newCommentRequest).enqueue(object : Callback<NewCommentResponse?> {
            override fun onResponse(call: Call<NewCommentResponse?>, response: Response<NewCommentResponse?>) {
                commentInterface.onNewCommentSuccess(response.body() as NewCommentResponse)
            }

            override fun onFailure(call: Call<NewCommentResponse?>, t: Throwable) {
            }
        })
    }

    fun tryGetComment(postIdx: Int, userIdx: Int){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.getComment(postIdx, userIdx).enqueue(object : Callback<GetCommentResponse?> {
            override fun onResponse(call: Call<GetCommentResponse?>, response: Response<GetCommentResponse?>) {
                commentInterface.onGetCommentSuccess(response.body() as GetCommentResponse)
            }

            override fun onFailure(call: Call<GetCommentResponse?>, t: Throwable) {
            }
        })
    }

    fun tryNewReply(newReplyRequest: NewReplyRequest){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.postReply(newReplyRequest).enqueue(object : Callback<NewReplyResponse?> {
            override fun onResponse(call: Call<NewReplyResponse?>, response: Response<NewReplyResponse?>) {
                commentInterface.onNewReplySuccess(response.body() as NewReplyResponse)
            }

            override fun onFailure(call: Call<NewReplyResponse?>, t: Throwable) {
            }
        })
    }
}