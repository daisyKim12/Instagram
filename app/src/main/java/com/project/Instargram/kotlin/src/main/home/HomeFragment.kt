package com.project.Instargram.kotlin.src.main.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.annotations.SerializedName
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseFragment
import com.project.Instargram.kotlin.databinding.BottomSheetHomeBinding
import com.project.Instargram.kotlin.databinding.FragmentHomeBinding
import com.project.Instargram.kotlin.src.main.TempPageLists
import com.project.Instargram.kotlin.src.main.home.adpater.StoryPostAdapter
import com.project.Instargram.kotlin.src.main.home.models.getFeed.GetPostResponse

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home), HomeInterface{

    private val KEY_USERID = "userIdx"
    private var userIdx = 0
    private var pagging = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setUpStoryPostRecyclerView()
        userIdx = getIntegerValue(KEY_USERID)!!
        Log.d(TAG, "onViewCreated: " + userIdx)

        HomeService(this).tryGetFeed(userIdx,pagging++)
    }

    override fun onResume() {
        super.onResume()
        binding.refresh.setOnRefreshListener {
            HomeService(this).tryGetFeed(userIdx,pagging++)
        }


    }

    private fun setUpStoryPostRecyclerView(getPostResponse: GetPostResponse) {
        binding.rvStorypost.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvStorypost.layoutManager = linearLayoutManager
        val adapter = StoryPostAdapter(requireContext(),  getPostResponse, TempPageLists.storyAdpSlides)
        //TempPageLists.postAdpSlides,
        adapter.notifyDataSetChanged()
        binding.rvStorypost.adapter = adapter
    }



    override fun onGetFeedSuccess(response: GetPostResponse) {
        Log.d(TAG, "onGetFeedSuccess: " + response)
        setUpStoryPostRecyclerView(response)
        binding.refresh.setRefreshing(false)

    }

    override fun onGetFeedFailure(message: String) {
        Log.d(TAG, "onGetFeedFailure: " + message)
    }


//    private fun showBottomSheetAdv() {
//        val bottomSheetDialog = BottomSheetDialog(requireContext())
//        val binding: BottomSheetHomeBinding = BottomSheetHomeBinding.inflate(layoutInflater)
//        bottomSheetDialog.setContentView(binding.root)
//    }
}