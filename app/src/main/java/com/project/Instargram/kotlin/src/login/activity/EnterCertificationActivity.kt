package com.project.Instargram.kotlin.src.login.activity

import android.content.Intent
import android.os.Bundle
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterCertificationBinding

class EnterCertificationActivity : BaseActivity<ActivityEnterCertificationBinding>(ActivityEnterCertificationBinding::inflate) {

    private val KEY_GET = "phone"
    private val KEY_INTENT = "certification"
    private lateinit var certificationNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        certificationNumber = intent.getStringExtra(KEY_INTENT).toString()

        binding.txtDetail1.text = "계정을 확인하려면 " +
                getValue(KEY_GET).toString() + "번으로 전송된 6자리 코드를 입력하세요."
    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }

        binding.btnNext.setOnClickListener {
            val data = binding.edittextPhonenum.text.toString()
            if(validCertificate(data)){
                binding.expandableLayout.collapse()
                startActivity(Intent(this, EnterNameActivity::class.java))
            } else {
                binding.expandableLayout.expand()
            }
        }

    }
    fun validCertificate(number: String): Boolean {
        if(number == certificationNumber) {
            return true
        } else {
            return false
        }
    }

}