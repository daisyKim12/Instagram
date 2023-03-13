package com.project.Instargram.kotlin.src.main.singlePost

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivitySinglePostBinding
import com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.GetSinglePostResponse
import com.project.Instargram.kotlin.src.main.home.adpater.PostImageAdapter
import com.project.Instargram.kotlin.src.main.singlePost.model.SinglePostInterface
import com.project.Instargram.kotlin.src.main.singlePost.model.SinglePostService
import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowResponse

class SinglePostActivity:BaseActivity<ActivitySinglePostBinding>(ActivitySinglePostBinding::inflate),
    SinglePostInterface {

    private var KEY_GET = "single_post"
    private val IS_IT_MINE = "isItMyPost"
    private val KEY_USERID = "userIdx"
    private var startFollow = true
    private var startLike = true

    private var userIdx = 0
    private var targetIdx = 0
    private var isItMyPost = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isItMyPost = intent.getBooleanExtra(IS_IT_MINE, true)

        val response = intent.extras?.getSerializable(KEY_GET) as GetSinglePostResponse
        val result = response.result
        Log.d(TAG, "onCreate: " + result)

        userIdx = getIntegerValue(KEY_USERID)!!.toInt()
        targetIdx = result.authorIdx


        if(isItMyPost == true) {
            binding.btnFollow.visibility = View.INVISIBLE
            binding.btnFollow.isClickable = false
        } else {
            binding.btnFollow.visibility = View.VISIBLE
            binding.btnFollow.isClickable = true
        }

        Glide.with(this).load(result.authorProfileImgURL).into(binding.imgThumbnail)
        binding.txtName.text = result.authorNickName
        //Glide.with(this).load(result.postFileURLList[0]).into(binding.vpPost)
        binding.txtLike.text = "좋아요 " + result.likeNumber.toString() + "개"
        binding.txtDetail.text = result.postText
        binding.txtTime.text = result.since.toString() + "전"

        setUpViewPager(this, result.postFileURLList)
    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }
        binding.btnFollow.setOnClickListener {
            //팔로우
            if(startFollow == true) {
                binding.btnFollow.text = "팔로우"
                startFollow = false
                //call follow api
                val newFollowRequest = NewFollowRequest(userIdx, targetIdx)
                SinglePostService(this).tryNewFollow(newFollowRequest)
            } else {
                binding.btnFollow.text = "팔로잉"
                startFollow = true
                //call unfollow api
                val newFollowRequest = NewFollowRequest(userIdx, targetIdx)
                SinglePostService(this).tryUnFollow(newFollowRequest)
            }
        }
        binding.btnBookmark.setOnClickListener {
            //북마크
        }
        binding.btnLike.setOnClickListener {
            //좋아요
            if(startLike == true) {

                startFollow = false
                //call follow api
                val newFollowRequest = NewFollowRequest(userIdx, targetIdx)
                SinglePostService(this).tryNewFollow(newFollowRequest)
            } else {

                startFollow = true
                //call unfollow api
                val newFollowRequest = NewFollowRequest(userIdx, targetIdx)
                SinglePostService(this).tryUnFollow(newFollowRequest)
            }
        }
    }

    override fun onNewFollowSuccess(response: NewFollowResponse) {
        Log.d(TAG, "onNewFollowSuccess: " + response)
    }

    override fun onNewFollowFailure(message: String) {
        Log.d(TAG, "onNewFollowFailure: " + message)
    }

    override fun onUnFollowSuccess(response: NewFollowResponse) {
        Log.d(TAG, "onUnFollowSuccess: " + response)
    }

    override fun onUnFollowFailure(message: String) {
        Log.d(TAG, "onUnFollowFailure: " + message)
    }

    fun setUpViewPager(context: Context, postUrlList: List<String>){
        val adapter = PostImageAdapter(context, postUrlList)
        val viewPager2 = binding.vpPost
        viewPager2?.adapter = adapter
        //viewPager2?.registerOnPageChangeCallback(pager2Callback)
        binding.dotsIndicator.setViewPager2(viewPager2!!)
    }
}