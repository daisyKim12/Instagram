package com.project.Instargram.kotlin.src.login

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterCertificationBinding

class EnterCertificationActivity : BaseActivity<ActivityEnterCertificationBinding>(ActivityEnterCertificationBinding::inflate) {

    private val KEY_GET = "phone"
    private val KEY_SEND = "certification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.txtDetail1.text = "계정을 확인하려면 " +
                getValue(KEY_GET).toString() + "번으로 전송된 6자리 코드를 입력하세요."
    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
//            val data = binding.edittextPhonenum.text.toString()
//            Log.d(ContentValues.TAG, "onResume: next clicked" + data)
//            if(validCellPhone(data)){
//                saveFromEditText(KEY_SEND, data)
//                startActivity(Intent(this, EnterCertificationActivity::class.java))
//            } else {
//                binding.txtDetail2.text = "휴대폰 번호가 정확하지 않습니다. " +
//                        "국가번호를 포함한 전체 휴대폰 번호를 입력해주세요."
//                binding.txtDetail2.setTextColor(ContextCompat.getColor(this, R.color.redForText))
//            }
        }

    }
}