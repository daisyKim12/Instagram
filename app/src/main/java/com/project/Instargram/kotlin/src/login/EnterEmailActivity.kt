package com.project.Instargram.kotlin.src.login

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
import com.project.Instargram.kotlin.databinding.ActivityEnterPhoneNumberBinding
import com.project.Instargram.kotlin.src.login.model.EmailAuthRequest
import com.project.Instargram.kotlin.src.login.model.EmailAuthResponse
import com.project.Instargram.kotlin.src.login.model.LoginService

class EnterEmailActivity : BaseActivity<ActivityEnterEmailBinding>(ActivityEnterEmailBinding::inflate), LoginActivityInterface {

    private val KEY_SEND = "email"
    private val networkingDone = false
    private val KEY_INTENT = "certification"
    private var certificationNumber = ""
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindProgressButton(binding.btnNext)

        handler = Handler(Looper.getMainLooper())
    }

    override fun onResume() {
        super.onResume()
        binding.tbBack.setOnClickListener {
            finish()
        }
        binding.btnNext.setOnClickListener {
            val data = binding.edittext.text.toString()
            Log.d(ContentValues.TAG, "onResume: next clicked" + data)
            if (validEmail(data)) {
                binding.txtDetail2.text = ""
                binding.txtDetail2.setTextColor(ContextCompat.getColor(this, R.color.blackForText))
                saveFromEditText(KEY_SEND, data)

                //email auth request
                val emailAuthRequest = EmailAuthRequest(data)
                LoginService(this).tryPostEmailAuth(emailAuthRequest)

                //start thread and when networking is finished move to next activity
                waitAndMoveToNextActivity()

                //startActivity(Intent(this, EnterCertificationActivity::class.java))
            } else {
                binding.txtDetail2.text = "유효한 이메일 주소를 입력하세요."
                binding.txtDetail2.setTextColor(ContextCompat.getColor(this, R.color.redForText))
            }
        }
    }

    fun validEmail(email: String): Boolean {

        return true
    }

    fun waitAndMoveToNextActivity() {

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

}