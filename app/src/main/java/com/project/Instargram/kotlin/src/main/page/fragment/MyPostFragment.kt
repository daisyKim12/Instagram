package com.project.Instargram.kotlin.src.main.page.fragment

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseFragment
import com.project.Instargram.kotlin.databinding.FragmentMyPostBinding
import com.project.Instargram.kotlin.src.main.singlePost.SinglePostActivity
import com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.GetSinglePostResponse
import com.project.Instargram.kotlin.src.main.page.adapter.MyPostAdapter
import com.project.Instargram.kotlin.src.main.page.model.post.GetUserPostResponse
import com.project.Instargram.kotlin.src.main.page.model.post.UserPostInterface
import com.project.Instargram.kotlin.src.main.page.model.post.UserPostService

class MyPostFragment : BaseFragment<FragmentMyPostBinding>(FragmentMyPostBinding::bind, R.layout.fragment_my_post)
    , UserPostInterface, MyPostAdapter.PhotoListener {

    private val KEY_USERID = "userIdx"
    private val IS_IT_MINE = "isItMyPost"
    private var KEY_SEND = "single_post"
    private val KEY_TARGET_IDX = "targetIdx_for_prfile"

    private var userIdx: Int = 0
    private var targetIdx = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userIdx = getIntegerValue(KEY_USERID)!!
        targetIdx = getIntegerValue(KEY_TARGET_IDX)!!

        // TODO
        //UserPostService(this).tryGetUserPost(targetIdx)
        UserPostService(this).tryGetUserPost(userIdx)

        //setUpMyPostRecyclerView()


    }

    override fun onPhotoClick(postIdx: Int) {
        UserPostService(this).tryGetSinglePost(userIdx, postIdx)
    }

    override fun onGetUserPostSuccess(response: GetUserPostResponse) {
        Log.d(TAG, "onGetUserPostSuccess: " + response)
        val result = response.result

        setUpMyPostRecyclerView(response)


    }

    override fun onGetUserPostFailure(message: String) {
        Log.d(TAG, "onGetUserPostFailure: " + message)
    }

    override fun onGetSinglePostSuccess(response: GetSinglePostResponse) {
        Log.d(TAG, "onGetSinglePostSuccess: " + response)
        val intent = Intent(activity, SinglePostActivity::class.java)
        intent.putExtra(KEY_SEND, response as java.io.Serializable)
        intent.putExtra(IS_IT_MINE, true)
        startActivity(intent)
    }

    override fun onGetSinglePostFailure(message: String) {
        Log.d(TAG, "onGetSinglePostFailure: " + message)
    }

    private fun setUpMyPostRecyclerView(response: GetUserPostResponse) {

        binding.rvMyPost.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false )
        binding.rvMyPost.layoutManager = gridLayoutManager
        val adapter = MyPostAdapter(requireContext(), response, this)
        adapter.notifyDataSetChanged()
        binding.rvMyPost.adapter = adapter
    }
}
