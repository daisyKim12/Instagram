package com.project.Instargram.kotlin.src.main.extra

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivitySinglePostBinding
import com.project.Instargram.kotlin.src.main.extra.model.GetSinglePostResponse
import com.project.Instargram.kotlin.src.main.home.adpater.PostImageAdapter

class SinglePostActivity:BaseActivity<ActivitySinglePostBinding>(ActivitySinglePostBinding::inflate) {

    private var KEY_GET = "single_post"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val response = intent.extras?.getSerializable(KEY_GET) as GetSinglePostResponse
        val result = response.result
        Log.d(TAG, "onCreate: " + result)

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
    }

    fun setUpViewPager(context: Context, postUrlList: List<String>){
        val adapter = PostImageAdapter(context, postUrlList)
        val viewPager2 = binding.vpPost
        viewPager2?.adapter = adapter
        //viewPager2?.registerOnPageChangeCallback(pager2Callback)
        binding.dotsIndicator.setViewPager2(viewPager2!!)
    }
}