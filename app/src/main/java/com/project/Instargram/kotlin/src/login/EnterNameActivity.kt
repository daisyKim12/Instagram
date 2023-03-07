package com.project.Instargram.kotlin.src.login

import android.content.Intent
import android.os.Bundle
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterNameBinding
class EnterNameActivity: BaseActivity<ActivityEnterNameBinding>(ActivityEnterNameBinding::inflate) {

    private val KEY_SEND = "name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
            val data = binding.edittextPhonenum.text.toString()

            saveFromEditText(KEY_SEND, data)
            startActivity(Intent(this, EnterPasswordActivity::class.java))
        }

    }



}