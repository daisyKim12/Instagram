package com.project.Instargram.kotlin.src.login.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import com.github.razir.progressbutton.showProgress
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterPhoneNumberBinding
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailAuthRequest
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailService
import com.project.Instargram.kotlin.src.login.model.PhoneAuth.PhoneActivityInterface
import com.project.Instargram.kotlin.src.login.model.PhoneAuth.PhoneDuplicateRequest
import com.project.Instargram.kotlin.src.login.model.PhoneAuth.PhoneDuplicateResponse
import com.project.Instargram.kotlin.src.login.model.PhoneAuth.PhoneService


class EnterPhoneActivity: BaseActivity<ActivityEnterPhoneNumberBinding>(ActivityEnterPhoneNumberBinding::inflate), PhoneActivityInterface {

    private val KEY_SEND = "phone"
    private var errorString = " "
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler = Handler(Looper.getMainLooper())
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

                val fdata = changePhoneFormat(data)
                saveString(KEY_SEND, fdata)
                Log.d(TAG, "onResume: "+fdata)
                val phoneDuplicateRequest = PhoneDuplicateRequest(fdata)
                PhoneService(this).tryPostPhoneDuplication(phoneDuplicateRequest)

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

    private fun changePhoneFormat(phone: String): String {
        return phone.slice(0..2) + "-" + phone.slice(3..6) + "-" + phone.slice(7..10)
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

    override fun onPostPhoneDuplicationSuccess(response: PhoneDuplicateResponse) {
        val code: String = response.code.toString()
        if(code == "1000") {
            errorString = ""
            binding.txtDetail2.text = errorString
            binding.txtDetail2.setTextColor(ContextCompat.getColor(this, R.color.redForText))
            waitAndMoveToNextActivity()
        } else if(code == "2010") {
            errorString = "이메일 형식을 확인해주세요"
            binding.txtDetail2.text = errorString
            binding.txtDetail2.setTextColor(ContextCompat.getColor(this, R.color.redForText))
        } else if(code == "3013") {
            errorString = "중복된 이메일입니다"
            binding.txtDetail2.text = errorString
            binding.txtDetail2.setTextColor(ContextCompat.getColor(this, R.color.redForText))
        } else {
            Log.d(TAG, "onPostEmailDuplicationSuccess: 모르는 오류 상태")
        }    }

    override fun onPostPhoneDuplicationFailure(message: String) {
        TODO("Not yet implemented")
    }
    private fun waitAndMoveToNextActivity() {

        ///button progress
        Thread() {
            handler.post(Runnable {
                binding.btnNext.showProgress { progressColor = Color.WHITE }
            })
            Thread.sleep(5000)
            startActivity(Intent(this, EnterNameActivity::class.java))
        }.start()

    }
}