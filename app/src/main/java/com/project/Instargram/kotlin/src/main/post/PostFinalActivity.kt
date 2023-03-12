package com.project.Instargram.kotlin.src.main.post

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityPostFinalBinding


class PostFinalActivity: BaseActivity<ActivityPostFinalBinding>(ActivityPostFinalBinding::inflate){

    private val KEY_SINGLE = "sigle_image"
    private val KEY_MULTI = "multi_image"
    private val ISITSINGLE = "is_it_single_image"
    private var imageNumber = 0

    private lateinit var imageToPost: String
    private lateinit var imagesToPost: ArrayList<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isItSingle = intent.getBooleanExtra(ISITSINGLE, true)
        if(isItSingle == true) {
            imageNumber = 1
            imageToPost = intent.getStringExtra(KEY_SINGLE).toString()

        } else {
            imagesToPost = intent.getStringArrayListExtra(KEY_MULTI)!!
            imageNumber = imagesToPost.size
        }

        Log.d(TAG, "imageCount: " + imageNumber)
    }


    override fun onResume() {
        super.onResume()

        if(imageNumber == 1) {
            Glide.with(this).load(imageToPost).into(binding.imgThumbnail)
        } else {
            Glide.with(this).load(imagesToPost[0]).into(binding.imgThumbnail)
        }

        binding.tbBack.setOnClickListener {
            finish()
        }
        binding.tbSave.setOnClickListener {

        }
    }

}
