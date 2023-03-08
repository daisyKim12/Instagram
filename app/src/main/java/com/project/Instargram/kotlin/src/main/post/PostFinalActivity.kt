package com.project.Instargram.kotlin.src.main.post

import android.os.Bundle
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityPostFinalBinding


class PostFinalActivity: BaseActivity<ActivityPostFinalBinding>(ActivityPostFinalBinding::inflate){

    private val KEY_SINGLE = "sigle_image"
    private val KEY_MULTI = "multi_image"
    private val BOOLEAN_MULTI = "is_it_a_multi_image"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }
        binding.tbSave.setOnClickListener {

        }
    }

}
