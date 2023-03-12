package com.project.Instargram.kotlin.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivitySplashBinding
import com.project.Instargram.kotlin.src.login.activity.LoginActivity
import com.project.Instargram.kotlin.src.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    private val KEY_SEND = "auto_login"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val autoSaved = getBooleanValue(KEY_SEND)

        Handler(Looper.getMainLooper()).postDelayed({
            //if auto saved is on
            if(autoSaved == true) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, 1500)
    }
}