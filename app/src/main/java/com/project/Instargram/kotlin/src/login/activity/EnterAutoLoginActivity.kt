package com.project.Instargram.kotlin.src.login.activity

import android.content.Intent
import android.os.Bundle
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterAutoLoginBinding

class EnterAutoLoginActivity: BaseActivity<ActivityEnterAutoLoginBinding>(ActivityEnterAutoLoginBinding::inflate) {

    private val KEY_SEND = "auto_login"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }

        binding.btnSaveNext.setOnClickListener {
            saveAutoLogin(KEY_SEND, true)
            startActivity(Intent(this, EnterBirthDateActivity::class.java))
        }

        binding.btnLaterNext.setOnClickListener {
            saveAutoLogin(KEY_SEND, false)
            startActivity(Intent(this, EnterBirthDateActivity::class.java))
        }


    }
}