package com.project.Instargram.kotlin.src.login.activity

import android.content.Intent
import android.os.Bundle
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterPasswordBinding

class EnterPasswordActivity: BaseActivity<ActivityEnterPasswordBinding>(ActivityEnterPasswordBinding::inflate) {

    private val KEY_SEND = "password"

    private val NO_PW = "비밀번호는 비워둘 수 없습니다."
    private val TOO_SHORT = "비밀번호가 너무 짧습니다. 6자 이상의 문자와 숫자 조합으로 더 긴 비밀번호를 만드세요."
    private val TOO_EASY = "This password is too easy to guess. Please create a new one"

    private lateinit var ERROR_TXT: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ERROR_TXT = ""

    }


    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
            val data:String = binding.edittextPhonenum.text.toString()

            if(validPassword(data)){
                binding.expandableLayout.collapse()
                saveString(KEY_SEND, data)
                startActivity(Intent(this, EnterAutoLoginActivity::class.java))
            } else {
                binding.txtDetail2.text = ERROR_TXT
                binding.expandableLayout.expand()
            }
        }

    }



    fun validPassword(number: String): Boolean {
        if(number.length == 0) {
            ERROR_TXT = NO_PW
            return false
        }
        if(number.length < 6) {
            ERROR_TXT = TOO_SHORT
            return false
        }

        if(false) {
            ERROR_TXT = TOO_EASY
            return false
        }

        return true
    }
}