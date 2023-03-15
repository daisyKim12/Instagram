package com.project.Instargram.kotlin.src.main.post.adapter

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.databinding.RvHomePostItemBinding
import com.project.Instargram.kotlin.src.main.comment.CommentActivity
import com.project.Instargram.kotlin.src.main.home.adpater.PostImageAdapter
import com.project.Instargram.kotlin.src.main.home.models.getFeed.Feed
import com.project.Instargram.kotlin.src.main.home.models.getFeed.FollowInfo
import com.project.Instargram.kotlin.src.main.page.UserPageActivity
import com.project.Instargram.kotlin.src.main.singlePost.model.SinglePostInterface
import com.project.Instargram.kotlin.src.main.singlePost.model.SinglePostService
import com.project.Instargram.kotlin.src.main.singlePost.model.bookmark.NewBookmarkRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.bookmark.NewBookmarkResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.follow.NewFollowResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.GetSinglePostResponse
import com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.Result
import com.project.Instargram.kotlin.src.main.singlePost.model.like.NewLikeRequest
import com.project.Instargram.kotlin.src.main.singlePost.model.like.NewLikeResponse

class PostViewHolder(private val context: Context, private val binding: RvHomePostItemBinding)
    : RecyclerView.ViewHolder(binding.root), SinglePostInterface {

    private val KEY_USERID = "userIdx"
    private val KEY_SEND = "not_my_userIdx"
//    private val KEY_FEED = "feed"
    private val KEY_PRE_COMMENT = "data_for_comment"

    private var userIdx = 0
    private var targetIdx = 0
    private var postIdx = 0

    private var startLike = true
    private var startSave = true

    fun bindItem(feed: Feed) {

        init(feed)

        binding.txtName.setOnClickListener {
            //유저 프로필 화면으로 전환
            val intent = Intent(context, UserPageActivity::class.java)
            intent.putExtra(KEY_SEND, targetIdx)
//            intent.putExtra(KEY_FEED, feed as java.io.Serializable)
            startActivity(context, intent, null)
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
            val intent = Intent(context, CommentActivity::class.java)
            val getSinglePostResponse = setSinglePostData(feed)
            intent.putExtra(KEY_PRE_COMMENT, getSinglePostResponse as java.io.Serializable)
            startActivity(context, intent, null)
        }
    }

    private fun setSinglePostData(feed: Feed): GetSinglePostResponse{
        val code: Int = 1000
        val isSuccess: Boolean = true
        val message: String = ""
        val authorIdx: Int = feed.authorIdx
        val authorNickName: String = feed.authorNickName
        val authorProfileImgURL: String = feed.authorProfileImgURL
        val hashTagList: List<String> = feed.hashTagList
        val likeNumber: Int = feed.likeNumber
        val location: String = feed.location
        val postFileURLList: List<String> = feed.postFileURLList
        val postIdx: Int= feed.postIdx
        val postText: String = feed.postText
        val since: Int = feed.since
        val taggedUserList: List<String> = feed.taggedUserList
        val isSavedPost: Boolean = feed.isSavedPost
        val isLikedPost: Boolean = feed.isLikedPost
        val commentNumber: Int = feed.commentNumber

        val result: Result = com.project.Instargram.kotlin.src.main.singlePost.model.getSinglePost.Result(
            authorIdx,authorNickName,authorProfileImgURL,hashTagList,likeNumber,
            location,postFileURLList,postIdx,postText,since,taggedUserList,isSavedPost,isLikedPost,commentNumber
        )
        return GetSinglePostResponse(code, isSuccess, message, result)
    }

    fun init(feed: Feed) {
        setValue(feed)

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

        Glide.with(context).load(feed.authorProfileImgURL).into(binding.imgThumbnail)
        binding.txtName.text = feed.authorNickName
        binding.txtLike.text = "좋아요 " + feed.likeNumber.toString() + "개"
        binding.txtDetail.text = feed.authorNickName + " " + feed.postText
        binding.txtComment.text = "댓글 " +
                feed.commentNumber.toString() +
                "개 보기"
        binding.txtTime.text = feed.since.toString()

        setUpViewPager(context, feed.postFileURLList)
    }

    fun setUpViewPager(context: Context, postUrlList: List<String>){
        val adapter = PostImageAdapter(context, postUrlList)
        val viewPager2 = binding.vpPost
        viewPager2?.adapter = adapter
        //viewPager2?.registerOnPageChangeCallback(pager2Callback)
        binding.dotsIndicator.setViewPager2(viewPager2!!)
    }

    fun setValue(feed: Feed) {
        userIdx = getIntegerValue(KEY_USERID)!!.toInt()
        targetIdx = feed.authorIdx
        postIdx = feed.postIdx
        startLike = !feed.isLikedPost
        startSave = !feed.isSavedPost
    }

    fun getIntegerValue(KEY: String): Int? {
        return ApplicationClass.sSharedPreferences.getInt(KEY, 0)
    }


    override fun onNewFollowSuccess(response: NewFollowResponse) {
        Log.d(ContentValues.TAG, "onNewFollowSuccess: " + response)
    }

    override fun onNewFollowFailure(message: String) {
        Log.d(ContentValues.TAG, "onNewFollowFailure: " + message)
    }

    override fun onUnFollowSuccess(response: NewFollowResponse) {
        Log.d(ContentValues.TAG, "onUnFollowSuccess: " + response)
    }

    override fun onUnFollowFailure(message: String) {
        Log.d(ContentValues.TAG, "onUnFollowFailure: " + message)
    }

    override fun onNewLikeSuccess(response: NewLikeResponse) {
        Log.d(ContentValues.TAG, "onNewLikeSuccess: " + response)
    }

    override fun onNewLikeFailure(message: String) {
        Log.d(ContentValues.TAG, "onNewLikeFailure: " + message)
    }

    override fun onUnLikeSuccess(response: NewLikeResponse) {
        Log.d(ContentValues.TAG, "onUnLikeSuccess: " + response)
    }

    override fun onUnLikeFailure(message: String) {
        Log.d(ContentValues.TAG, "onUnLikeFailure: " + message)
    }

    override fun onNewBookmarkSuccess(response: NewBookmarkResponse) {
        Log.d(ContentValues.TAG, "onNewBookmarkSuccess: " + response)
    }

    override fun onNewBookmarkFailure(message: String) {
        Log.d(ContentValues.TAG, "onNewBookmarkFailure: " + message)
    }

    override fun onUnBookmarkSuccess(response: NewBookmarkResponse) {
        Log.d(ContentValues.TAG, "onUnBookmarkSuccess: " + response)
    }

    override fun onUnBookmarkFailure(message: String) {
        Log.d(ContentValues.TAG, "onUnBookmarkFailure: " + message)
    }
}