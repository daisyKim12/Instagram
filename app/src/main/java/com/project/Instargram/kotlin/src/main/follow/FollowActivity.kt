package com.project.Instargram.kotlin.src.main.follow

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityFollowBinding
import com.project.Instargram.kotlin.src.main.follow.adpater.FollowerAdapter
import com.project.Instargram.kotlin.src.main.follow.adpater.FollowingAdapter
import com.project.Instargram.kotlin.src.main.follow.model.FollowService
import com.project.Instargram.kotlin.src.main.follow.model.FollowInterface
import com.project.Instargram.kotlin.src.main.follow.model.follower.GetFollowerResponse
import com.project.Instargram.kotlin.src.main.follow.model.following.GetFollowingResponse

class FollowActivity: BaseActivity<ActivityFollowBinding>(ActivityFollowBinding::inflate)
    , FollowInterface {

    private val KEY_USERID = "userIdx"
    private val KEY_GET = "nickName"
    private val KEY_FLAG = "is_it_follower"
    private val KEY_FOLLOWER_NM="follower"
    private val KEY_FOLLOWING_NM="following"
    private var userIdx = 0

    private var isItFollower = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userIdx = getIntegerValue(KEY_USERID)!!
        val nickname = intent.getStringExtra(KEY_GET).toString()
        val followerNm = intent.getStringExtra(KEY_FOLLOWER_NM).toString()
        val followingNm= intent.getStringExtra(KEY_FOLLOWING_NM).toString()
        isItFollower = intent.getBooleanExtra(KEY_FLAG, true)
        binding.tbProfile.text = nickname

        binding.tabLayout.getTabAt(0)?.text = "팔로워 " + followerNm +"명"
        binding.tabLayout.getTabAt(1)?.text = "팔로잉 " + followingNm +"명"


        if(isItFollower == true) {
            binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0))
            FollowService(this).tryGetFollower(userIdx)
        } else {
            binding.tabLayout.selectTab(binding.tabLayout.getTabAt(1))
            FollowService(this).tryGetFollowing(userIdx)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.tbBack.setOnClickListener {
            finish()
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab!!.position == 0) {
                    //follower adapter
                    FollowService(this@FollowActivity).tryGetFollower(userIdx)
                }else {
                    //following adapter
                    FollowService(this@FollowActivity).tryGetFollowing(userIdx)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }



    override fun onGetFollowerSuccess(response: GetFollowerResponse) {
        Log.d(TAG, "onGetFollowerSuccess: " + response)
        if(response.result.size == 0) {
            binding.imgBlank.visibility = View.VISIBLE
            binding.imgBlank.setBackgroundResource(com.project.Instargram.kotlin.R.drawable.follower_bg)
        } else {
            binding.imgBlank.visibility = View.INVISIBLE
            setFollowerRecyclerView(response)
        }
    }

    override fun onGetFollowerFailure(message: String) {
    }

    override fun onGetFollowingSuccess(response: GetFollowingResponse) {
        Log.d(TAG, "onGetFollowingSuccess: " + response)
        if(response.result.size == 0) {
            binding.imgBlank.visibility = View.VISIBLE
            binding.imgBlank.setBackgroundResource(com.project.Instargram.kotlin.R.drawable.following_bg)
        } else {
            binding.imgBlank.visibility = View.INVISIBLE
            setFollowingRecyclerView(response)
        }
    }

    override fun onGetFollowingFailure(message: String) {
    }

    private fun setFollowerRecyclerView(getFollowerResponse: GetFollowerResponse) {
        binding.rvFollow.setHasFixedSize(true)
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvFollow.layoutManager = linearLayoutManager
        val adapter = FollowerAdapter(this, getFollowerResponse)
        adapter.notifyDataSetChanged()
        binding.rvFollow.adapter = adapter
    }

    private fun setFollowingRecyclerView(getFollowingResponse: GetFollowingResponse) {
        binding.rvFollow.setHasFixedSize(true)
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvFollow.layoutManager = linearLayoutManager
        val adapter = FollowingAdapter(this, getFollowingResponse)
        adapter.notifyDataSetChanged()
        binding.rvFollow.adapter = adapter
    }
}