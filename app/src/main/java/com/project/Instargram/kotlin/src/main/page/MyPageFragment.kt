package com.project.Instargram.kotlin.src.main.page

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseFragment
import com.project.Instargram.kotlin.databinding.FragmentMyPageBinding
import com.project.Instargram.kotlin.src.main.TempPageLists
import com.project.Instargram.kotlin.src.main.follow.FollowActivity
import com.project.Instargram.kotlin.src.main.page.adapter.StoryHighlightAdapter
import com.project.Instargram.kotlin.src.main.page.adapter.TabFragmentAdapter
import com.project.Instargram.kotlin.src.main.page.model.profile.PageService
import com.project.Instargram.kotlin.src.main.page.model.profile.GetProfileResponse
import com.project.Instargram.kotlin.src.main.page.model.profile.ProfileInterface
import com.project.Instargram.kotlin.util.SettingActivity

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page),
    ProfileInterface {

    private val KEY_USERID = "userIdx"
    private val KEY_SEND = "nickName"
    private val KEY_FLAG = "is_it_follower"
    private val KEY_FOLLOWER_NM="follower"
    private val KEY_FOLLOWING_NM="following"
    private val KEY_TARGETID = "targetIdx"

    private val KEY_NICK_NAME = "nickName"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userIdx = getIntegerValue(KEY_USERID)!!
        Log.d(TAG, "onViewCreated: userIdx -> " + userIdx )
        saveInteger(KEY_TARGETID, userIdx)
        PageService(this).tryGetProfile(userIdx, userIdx)


        setAdapterForFragment()
        setStoryHightlightRecyclerView()
        binding.txtStory.setOnClickListener{
            binding.expandableLayout.expand()
        }
    }

    override fun onResume() {
        super.onResume()

        val intent = Intent(activity, FollowActivity::class.java)
        val nickName = binding.tbProfile.text.toString()
        val followerNm = binding.txtFollowerNm.text.toString()
        val followingNm = binding.txtFollowingNm.text.toString()
        intent.putExtra(KEY_SEND, nickName)
        intent.putExtra(KEY_FOLLOWER_NM, followerNm)
        intent.putExtra(KEY_FOLLOWING_NM, followingNm)
        binding.txtFollowerNm.setOnClickListener {
            intent.putExtra(KEY_FLAG, true)
            startActivity(intent)
        }
        binding.txtFollower.setOnClickListener {
            intent.putExtra(KEY_FLAG, true)
            startActivity(intent)
        }
        binding.txtFollowingNm.setOnClickListener {
            intent.putExtra(KEY_FLAG, false)
            startActivity(intent)
        }
        binding.txtFollowing.setOnClickListener {
            intent.putExtra(KEY_FLAG, false)
            startActivity(intent)
        }
        binding.txtPostNm.setOnClickListener {
            binding.appbarLayout.setExpanded(false)
        }
        binding.tbNav.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onGetProfileSuccess(response: GetProfileResponse) {
        Log.d(TAG, "onGetProfileSuccess: " + response)
        val result = response.result
        val nickName = result.userNickName
        val name: String = result.userName
        val imageUrl: String = result.profileImgUrl
        val postNumber: Int = result.postNumber
        val followingNumber: Int = result.followingNumber
        val followerNumber: Int = result.followerNumber
        val userDescription: String = result.userDescription

        //TODO nickname 문제 생기면 이거 확인
        saveString(KEY_NICK_NAME, nickName)

        binding.tbProfile.text = nickName
        binding.txtName.text = name
        Glide.with(this).load(imageUrl).into(binding.imgThumbnail)
        binding.txtPostNm.text = postNumber.toString()
        binding.txtFollowingNm.text = followingNumber.toString()
        binding.txtFollowerNm.text = followerNumber.toString()
//        if(userDescription != "NULL") {
//            binding.txtHashtag.text = ""
//            binding.txtDetail.text = userDescription
//        }
    }

    override fun onGetProfileFailure(message: String) {
        Log.d(TAG, "onGetProfileFailure: " + message)
    }

    private fun setAdapterForFragment() {
        val adapter = TabFragmentAdapter(parentFragmentManager, lifecycle)

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
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvStoryHightlight.layoutManager = linearLayoutManager
        val adapter = StoryHighlightAdapter(requireContext(), TempPageLists.storyHighlightAdpSlides)
        adapter.notifyDataSetChanged()
        binding.rvStoryHightlight.adapter = adapter
    }
}