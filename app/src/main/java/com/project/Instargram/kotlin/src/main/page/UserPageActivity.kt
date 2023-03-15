package com.project.Instargram.kotlin.src.main.page

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityUserPageBinding
import com.project.Instargram.kotlin.src.main.TempPageLists
import com.project.Instargram.kotlin.src.main.home.models.getFeed.Feed
import com.project.Instargram.kotlin.src.main.page.adapter.StoryHighlightAdapter
import com.project.Instargram.kotlin.src.main.page.adapter.TabFragmentAdapter
import com.project.Instargram.kotlin.src.main.page.model.profile.GetProfileResponse
import com.project.Instargram.kotlin.src.main.page.model.profile.PageService
import com.project.Instargram.kotlin.src.main.page.model.profile.ProfileInterface
import com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.GetSinglePostResponse

class UserPageActivity: BaseActivity<ActivityUserPageBinding>(ActivityUserPageBinding::inflate),
    ProfileInterface {

    private val KEY_GET = "not_my_userIdx"
    private val KEY_FEED = "feed"

    private var targetIdx = 0
    private lateinit var feed: Feed


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAdapterForFragment()
        setStoryHightlightRecyclerView()

        targetIdx = intent.getIntExtra(KEY_GET, 0)
        Log.d(TAG, "onViewCreated: userIdx -> " + targetIdx )

        feed = intent.extras?.getSerializable(KEY_FEED) as Feed
        Log.d(TAG, "onCreate: feed-> " + feed)

//        PageService(this).tryGetProfile(targetIdx)

    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }

    }

    override fun onGetProfileSuccess(response: GetProfileResponse) {
//        Log.d(ContentValues.TAG, "onGetProfileSuccess: " + response)
//        val result = response.result
//        val nickName: String = result.userNickName
//        val name: String = result.userName
//        val imageUrl: String = result.profileImgUrl
//        val postNumber: Int = result.postNumber
//        val followingNumber: Int = result.followingNumber
//        val followerNumber: Int = result.followerNumber
//
//        binding.tbProfile.text = nickName
//        binding.txtName.text = name
//        Glide.with(this).load(imageUrl).into(binding.imgThumbnail)
//        binding.txtPosterNm.text = postNumber.toString()
//        binding.txtFollowingNm.text = followingNumber.toString()
//        binding.txtFollowerNm.text = followerNumber.toString()
    }

    override fun onGetProfileFailure(message: String) {
        Log.d(ContentValues.TAG, "onGetProfileFailure: " + message)
    }

    private fun setAdapterForFragment() {
        val adapter = TabFragmentAdapter(this.supportFragmentManager, lifecycle)

        binding.container.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab != null) {
                    binding.container.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.container.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }
    private fun setStoryHightlightRecyclerView() {
        binding.rvStoryHightlight.setHasFixedSize(true)
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvStoryHightlight.layoutManager = linearLayoutManager
        val adapter = StoryHighlightAdapter(this, TempPageLists.storyHighlightAdpSlides)
        adapter.notifyDataSetChanged()
        binding.rvStoryHightlight.adapter = adapter
    }
}