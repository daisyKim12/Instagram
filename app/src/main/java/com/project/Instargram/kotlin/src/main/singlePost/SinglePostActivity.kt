package com.project.Instargram.kotlin.src.main.singlePost

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivitySinglePostBinding
import com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.GetSinglePostResponse
import com.project.Instargram.kotlin.src.main.home.adpater.PostImageAdapter
import com.project.Instargram.kotlin.src.main.page.UserPageActivity
import com.project.Instargram.kotlin.src.main.singlePost.model.SinglePostInterface
import com.project.Instargram.kotlin.src.main.singlePost.model.SinglePostService
import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.like.NewLikeRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.like.NewLikeResponse

class SinglePostActivity:BaseActivity<ActivitySinglePostBinding>(ActivitySinglePostBinding::inflate),
    SinglePostInterface {

    private val KEY_GET = "single_post"
    private val KEY_SEND = "not_my_userIdx"
    private val IS_IT_MINE = "isItMyPost"
    private val KEY_USERID = "userIdx"
    private var startFollow = true
    private var startLike = true

    private var userIdx = 0
    private var targetIdx = 0
    private var postIdx = 0

    private var isItMyPost = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isItMyPost = intent.getBooleanExtra(IS_IT_MINE, true)

        val response = intent.extras?.getSerializable(KEY_GET) as GetSinglePostResponse
        val result = response.result
        Log.d(TAG, "onCreate: " + result)

        userIdx = getIntegerValue(KEY_USERID)!!.toInt()
        targetIdx = result.authorIdx
        postIdx = result.postIdx

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

        binding.txtName.setOnClickListener {
            //유저 프로필 화면으로 전환
            val intent = Intent(this, UserPageActivity::class.java)
            intent.putExtra(KEY_SEND, targetIdx)
            startActivity(intent)
        }
        binding.btnFollow.setOnClickListener {
            //팔로우
            val newFollowRequest = NewFollowRequest(userIdx, targetIdx)
            if(startFollow == true) {
                binding.btnFollow.text = "팔로우"
                startFollow = false
                //call follow api
                SinglePostService(this).tryNewFollow(newFollowRequest)
            } else {
                binding.btnFollow.text = "팔로잉"
                startFollow = true
                //call unfollow api
                SinglePostService(this).tryUnFollow(newFollowRequest)
            }
        }
        binding.btnBookmark.setOnClickListener {
            //북마크
        }
        binding.btnLike.setOnClickListener {
            //좋아요
            val newlikeRequest = NewLikeRequest(postIdx, userIdx)
            Log.d(TAG, "onResume: eeee" + newlikeRequest)
            if(startLike == true) {
                binding.btnLike.setImageResource(com.project.Instargram.kotlin.R.drawable.ic_like_clicked)
                startLike = false
                //call follow api
                SinglePostService(this).tryNewLike(newlikeRequest)
            } else {
                binding.btnLike.setImageResource(com.project.Instargram.kotlin.R.drawable.ic_like_unclicked)
                startLike = true
                //call unfollow api
                SinglePostService(this).tryUnLike(newlikeRequest)
            }
        }
    }

    fun setUpViewPager(context: Context, postUrlList: List<String>){
        val adapter = PostImageAdapter(context, postUrlList)
        val viewPager2 = binding.vpPost
        viewPager2?.adapter = adapter
        //viewPager2?.registerOnPageChangeCallback(pager2Callback)
        binding.dotsIndicator.setViewPager2(viewPager2!!)
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

    override fun onNewLikeSuccess(response: NewLikeResponse) {
        Log.d(TAG, "onNewLikeSuccess: " + response)
    }

    override fun onNewLikeFailure(message: String) {
        Log.d(TAG, "onNewLikeFailure: " + message)
    }

    override fun onUnLikeSuccess(response: NewLikeResponse) {
        Log.d(TAG, "onUnLikeSuccess: " + response)
    }

    override fun onUnLikeFailure(message: String) {
        Log.d(TAG, "onUnLikeFailure: " + message)
    }
}