package com.project.Instargram.kotlin.src.login.activity

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.showProgress
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityLoginBinding
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailDuplicateRequest
import com.project.Instargram.kotlin.src.login.model.EmailAuth.EmailService
import com.project.Instargram.kotlin.src.login.model.login.EmailLoginRequest
import com.project.Instargram.kotlin.src.login.model.login.EmailLoginResponse
import com.project.Instargram.kotlin.src.login.model.login.LoginActivityInterface
import com.project.Instargram.kotlin.src.login.model.login.LoginService
import com.project.Instargram.kotlin.src.main.MainActivity
import com.project.Instargram.kotlin.util.LoadingDialog
import okhttp3.internal.wait


class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), LoginActivityInterface {

    private val KEY_USERID = "userIdx"
    private val KEY_TOKEN = "jwtToken"

    private var id: String = ""
    private var pw: String = ""
    private var idISEmail: Boolean = true

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindProgressButton(binding.btnLogin)

        handler = Handler(Looper.getMainLooper())

    }

    override fun onResume() {
        super.onResume()

        binding.btnLogin.setOnClickListener {
            id = binding.edittextId.text.toString()
            pw = binding.edittextPw.text.toString()

            checkID(id)
            checkPW(pw)

            if(idISEmail == true) {
                val emailLoginRequest = EmailLoginRequest(id, pw)
                LoginService(this).tryPostEmailLogin(emailLoginRequest)
            }
        }

        binding.btnNewAccount.setOnClickListener {
            startActivity(Intent(this, EnterPhoneActivity::class.java))
            finish()
        }
    }

    private fun checkPW(pw: String) {
        if(pw.length == 0) {
            showErroDialog(this, "비밀번호 필요", "계속하려면 비밀번호를 입력하세요.")
        }
    }

    private fun checkID(id: String) {
        if(id.length < 3) {
            showErroDialog(this, "이메일 주소 또는 휴대폰 번호 필요", "계속하려면 이메일 주소 또는 휴대폰\\n번호를 입력하세요.")
        } else if (id.slice(0..2) == "010") {
            idISEmail = false
        } else {
            idISEmail = true
        }
    }

    private fun waitAndMoveToNextActivity() {
        ///button progress
        Thread() {
            handler.post(Runnable {
                binding.btnLogin.showProgress { progressColor = Color.WHITE }
            })
            Thread.sleep(5000)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.start()

    }

    override fun onPostEmailLoginSuccess(response: EmailLoginResponse) {
        val code: String = response.code.toString()
        Log.d(TAG, "onPostEmailLoginSuccess: "+ response)
//        saveString(KEY_USERID, response.result.userIdx)
//        saveString(KEY_TOKEN, response.result.jwtToken)
//        waitAndMoveToNextActivity()
        if (code == "1000") {
            saveInteger(KEY_USERID, response.result.userIdx)
            saveString(KEY_TOKEN, response.result.jwtToken)
            waitAndMoveToNextActivity()
        } else {
            showErroDialog(this, "잘못된 비밀번호", "입력된 비밀번호가 올바르지 않습니다.\n다시 시도하세요.")
            Log.d(TAG, "onPostEmailLoginSuccess: 비밀번호 복고화 실패")
        }
    }
    override fun onPostEmailLoginFailure(message: String) {
        Log.d(TAG, "onPostEmailLoginFailure: " + message)
    }

}