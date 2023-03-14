package com.project.Instargram.kotlin.src.main.comment

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.main.MainRetrofitInterface
import com.project.Instargram.kotlin.src.main.comment.model.getComment.GetCommentResponse
import com.project.Instargram.kotlin.src.main.comment.model.getReply.GetReplyResponse
import com.project.Instargram.kotlin.src.main.comment.model.newComment.NewCommentRequest
import com.project.Instargram.kotlin.src.main.comment.model.newComment.NewCommentResponse
import com.project.Instargram.kotlin.src.main.comment.model.newReply.NewReplyRequest
import com.project.Instargram.kotlin.src.main.comment.model.newReply.NewReplyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReplyService(val replyInterface: ReplyInterface) {

    fun tryGetReply(postIdx: Int, commentIdx: Int, userIdx: Int){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.getReply(postIdx, commentIdx, userIdx).enqueue(object : Callback<GetReplyResponse?> {
            override fun onResponse(call: Call<GetReplyResponse?>, response: Response<GetReplyResponse?>) {
                replyInterface.onGetReplySuccess(response.body() as GetReplyResponse)
            }

            override fun onFailure(call: Call<GetReplyResponse?>, t: Throwable) {
            }
        })
    }
}