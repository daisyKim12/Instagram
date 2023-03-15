package com.project.Instargram.kotlin.util

import android.content.Intent
import android.os.Bundle
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivitySettingBinding
import com.project.Instargram.kotlin.databinding.ActivityUserPageBinding
import com.project.Instargram.kotlin.src.login.activity.LoginActivity
import com.project.Instargram.kotlin.src.main.MainActivity
import com.project.Instargram.kotlin.src.main.page.model.profile.ProfileInterface

class SettingActivity: BaseActivity<ActivitySettingBinding>(ActivitySettingBinding::inflate){

    private val KEY_IMAGE = "image_path"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }
        binding.img3.setOnClickListener {
            val imagePath = getStringValue(KEY_IMAGE)!!
            clearAllSharedPref()
            saveString(KEY_IMAGE, imagePath)
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)        }
    }
}