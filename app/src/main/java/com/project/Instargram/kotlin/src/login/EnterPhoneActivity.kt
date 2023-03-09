package com.project.Instargram.kotlin.src.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterPhoneNumberBinding


class EnterPhoneActivity: BaseActivity<ActivityEnterPhoneNumberBinding>(ActivityEnterPhoneNumberBinding::inflate) {

    private val KEY_SEND = "phone"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        binding.tbBack.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        binding.btnNext.setOnClickListener {
            val data = binding.edittextPhonenum.text.toString()
            Log.d(TAG, "onResume: next clicked" + data)
            if(validCellPhone(data)){
                binding.txtDetail2.text = "보안 및 로그인 목적으로 Instagram에서 보내는 SMS 알림을 수실할 수 있습니다."
                binding.txtDetail2.setTextColor(ContextCompat.getColor(this, R.color.blackForText))
                saveFromEditText(KEY_SEND, data)
                startActivity(Intent(this, EnterCertificationActivity::class.java))
            } else {
                binding.txtDetail2.text = "휴대폰 번호가 정확하지 않습니다. " +
                        "국가번호를 포함한 전체 휴대폰 번호를 입력해주세요."
                binding.txtDetail2.setTextColor(ContextCompat.getColor(this, R.color.redForText))
            }
        }
        binding.btnEmail.setOnClickListener {
            startActivity(Intent(this, EnterEmailActivity::class.java))
        }
    }

    fun validCellPhone(number: String): Boolean {
        if(number.length != 11) {
            return false
        }
        if(number.slice(0..2) != "010") {
            return false
        }
        return true
    }
}