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
import com.project.Instargram.kotlin.src.main.comment.CommentActivity
import com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.GetSinglePostResponse
import com.project.Instargram.kotlin.src.main.home.adpater.PostImageAdapter
import com.project.Instargram.kotlin.src.main.page.UserPageActivity
import com.project.Instargram.kotlin.src.main.singlePost.model.SinglePostInterface
import com.project.Instargram.kotlin.src.main.singlePost.model.SinglePostService
import com.project.Instargram.kotlin.src.main.singlePost.model.bookmark.NewBookmarkRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.bookmark.NewBookmarkResponse
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
    private val KEY_PRE_COMMENT = "data_for_comment"

    private var startFollow = true
    private var startLike = true
    private var startSave = true

    private var userIdx = 0
    private var targetIdx = 0
    private var postIdx = 0
    private lateinit var singlePostData: GetSinglePostResponse

    private var isItMyPost = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isItMyPost = intent.getBooleanExtra(IS_IT_MINE, true)

        val response = intent.extras?.getSerializable(KEY_GET) as GetSinglePostResponse
        singlePostData = response
        val result = response.result
        Log.d(TAG, "onCreate: " + result)

        userIdx = getIntegerValue(KEY_USERID)!!.toInt()
        targetIdx = result.authorIdx
        postIdx = result.postIdx
        startLike = !result.isLikedPost
        startSave = !result.isSavedPost

        if(isItMyPost == true) {
            binding.btnFollow.visibility = View.INVISIBLE
            binding.btnFollow.isClickable = false
        } else {
            binding.btnFollow.visibility = View.VISIBLE
            binding.btnFollow.isClickable = true
        }

        if(startLike == true) {
            binding.btnLike.setImageResource(com.project.Instargram.kotlin.R.drawable.ic_like_unclicked)
        } else {
            binding.btnLike.setImageResource(com.project.Instargram.kotlin.R.drawable.ic_like_clicked)
        }

        if(startSave == true) {
            binding.btnBookmark.setImageResource(com.project.Instargram.kotlin.R.drawable.ic_bookmark_unclicked)
        } else {
            binding.btnBookmark.setImageResource(com.project.Instargram.kotlin.R.drawable.ic_bookmark_clicked)
        }

        Glide.with(this).load(result.authorProfileImgURL).into(binding.imgThumbnail)
        binding.txtName.text = result.authorNickName
        //Glide.with(this).load(result.postFileURLList[0]).into(binding.vpPost)
        binding.txtLike.text = "좋아요 " + result.likeNumber.toString() + "개"
        binding.txtDetail.text = result.postText
        binding.txtTime.text = result.since.toString() + "전"
        binding.txtComment.text = "댓글 " +
                result.commentNumber.toString() +
                "개 보기"

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
            val newBookmarkRequest = NewBookmarkRequest(postIdx, userIdx)
            if(startSave == true) {
                binding.btnBookmark.setImageResource(com.project.Instargram.kotlin.R.drawable.ic_bookmark_clicked)
                startSave = false
                SinglePostService(this).tryNewBookmark(newBookmarkRequest)
            } else {
                binding.btnBookmark.setImageResource(com.project.Instargram.kotlin.R.drawable.ic_bookmark_unclicked)
                startSave = true
                SinglePostService(this).tryUnBookmark(newBookmarkRequest)
            }
        }
        binding.btnLike.setOnClickListener {
            //좋아요
            val newlikeRequest = NewLikeRequest(postIdx, userIdx)
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
        binding.txtComment.setOnClickListener {
            val intent = Intent(this, CommentActivity::class.java)
            intent.putExtra(KEY_PRE_COMMENT, singlePostData as java.io.Serializable)
            //intent.putExtra(KEY_COMMENT, response as java.io.Serializable)
            startActivity(intent)
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

    override fun onNewBookmarkSuccess(response: NewBookmarkResponse) {
        Log.d(TAG, "onNewBookmarkSuccess: " + response)
    }

    override fun onNewBookmarkFailure(message: String) {
        Log.d(TAG, "onNewBookmarkFailure: " + message)
    }

    override fun onUnBookmarkSuccess(response: NewBookmarkResponse) {
        Log.d(TAG, "onUnBookmarkSuccess: " + response)
    }

    override fun onUnBookmarkFailure(message: String) {
        Log.d(TAG, "onUnBookmarkFailure: " + message)
    }
}