package com.project.Instargram.kotlin.src.login.activity

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.content.ContextCompat
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.showProgress
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterEmailBinding
import com.project.Instargram.kotlin.src.login.model.EmailAuth.*

class EnterEmailActivity : BaseActivity<ActivityEnterEmailBinding>(ActivityEnterEmailBinding::inflate), EmailActivityInterface {

    private val KEY_SEND = "email"
    private var data = ""
    private val networkingDone = false
    private val KEY_INTENT = "certification"
    private var certificationNumber = ""
    private var errorString = " "
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        errorString = "유효한 이메일 주소를 입력하세요."
        bindProgressButton(binding.btnNext)

        handler = Handler(Looper.getMainLooper())
    }

    override fun onResume() {
        super.onResume()
        binding.tbBack.setOnClickListener {
            finish()
        }
        binding.btnNext.setOnClickListener {
            data = binding.edittext.text.toString()
            Log.d(ContentValues.TAG, "onResume: next clicked" + data)
            if (validEmail(data)) {
                binding.txtDetail2.text = ""
                binding.txtDetail2.setTextColor(ContextCompat.getColor(this, R.color.blackForText))
                saveFromEditText(KEY_SEND, data)

                val emailDuplicateRequest = EmailDuplicateRequest(data)
                EmailService(this).tryPostEmailDuplication(emailDuplicateRequest)

            } else {
                binding.txtDetail2.text = errorString
                binding.txtDetail2.setTextColor(ContextCompat.getColor(this, R.color.redForText))
            }
        }
    }

    private fun validEmail(email: String): Boolean {

        return true
    }

    private fun waitAndMoveToNextActivity() {

        ///button progress
        Thread() {
            handler.post(Runnable {
                binding.btnNext.showProgress { progressColor = Color.WHITE }
            })
            Thread.sleep(5000)
            Log.d(TAG, "waitAndMoveToNext: "+ certificationNumber)
            val intent = Intent(this, EnterCertificationActivity::class.java)
            intent.putExtra(KEY_INTENT, certificationNumber)
            startActivity(intent)
        }.start()

    }


    override fun onPostEmailAuthSuccess(response: EmailAuthResponse) {
        certificationNumber = response.result.authKey.toString()
//        Log.d(TAG, "onPostEmailAuthSuccess: "+ certificationNumber)
    }

    override fun onPostEmailAuthFailure(message: String) {
        Log.d(TAG, "onPostEmailAuthFailure: "+ message)
    }

    override fun onPostEmailDuplicationSuccess(response: EmailDuplicateResponse) {
        val code: String = response.code.toString()
        if(code == "1000") {
            errorString = ""
            binding.txtDetail2.text = errorString
            binding.txtDetail2.setTextColor(ContextCompat.getColor(this, R.color.redForText))
            //send
            //email auth request
            val emailAuthRequest = EmailAuthRequest(data)
            EmailService(this).tryPostEmailAuth(emailAuthRequest)

            //start thread and when networking is finished move to next activity
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
        }
    }

    override fun onPostEmailDuplicationFailure(message: String) {
        Log.d(TAG, "onPostEmailDuplicationFailure: "+ message)
    }

}