package com.project.Instargram.kotlin.src.login

import android.content.Intent
import android.os.Bundle
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterNameBinding
import com.project.Instargram.kotlin.databinding.ActivityEnterNicknameBinding

class EnterNicknameActivity: BaseActivity<ActivityEnterNicknameBinding>(ActivityEnterNicknameBinding::inflate) {

    private val KEY_SEND = "nickName"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
            val data:String = binding.edittext.text.toString()

            saveFromEditText(KEY_SEND, data)
            startActivity(Intent(this, EnterTermsActivity::class.java))
        }

    }
}