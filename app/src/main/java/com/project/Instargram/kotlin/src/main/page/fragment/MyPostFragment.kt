package com.project.Instargram.kotlin.src.main.page.fragment

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseFragment
import com.project.Instargram.kotlin.databinding.FragmentMyPostBinding
import com.project.Instargram.kotlin.src.main.TempPageLists
import com.project.Instargram.kotlin.src.main.page.adapter.MyPostAdapter
import com.project.Instargram.kotlin.src.main.page.model.post.GetUserPostResponse
import com.project.Instargram.kotlin.src.main.page.model.post.UserPostInterface
import com.project.Instargram.kotlin.src.main.page.model.post.UserPostService
import com.project.Instargram.kotlin.src.main.page.model.profile.PageService

class MyPostFragment : BaseFragment<FragmentMyPostBinding>(FragmentMyPostBinding::bind, R.layout.fragment_my_post), UserPostInterface {

    private val KEY_USERID = "userIdx"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userIdx = getIntegerValue(KEY_USERID)!!
        Log.d(ContentValues.TAG, "onViewCreated: userIdx -> " + userIdx )
        UserPostService(this).tryGetUserPost(userIdx)

        //setUpMyPostRecyclerView()


    }

    override fun onGetUserPostSuccess(response: GetUserPostResponse) {
        Log.d(TAG, "onGetUserPostSuccess: " + response)
        val result = response.result

        setUpMyPostRecyclerView(response)


    }

    override fun onGetUserPostFailure(message: String) {
        Log.d(TAG, "onGetUserPostFailure: " + message)
    }

    private fun setUpMyPostRecyclerView(response: GetUserPostResponse) {

        binding.rvMyPost.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false )
        binding.rvMyPost.layoutManager = gridLayoutManager
        val adapter = MyPostAdapter(requireContext(), response)
        adapter.notifyDataSetChanged()
        binding.rvMyPost.adapter = adapter
    }
}
