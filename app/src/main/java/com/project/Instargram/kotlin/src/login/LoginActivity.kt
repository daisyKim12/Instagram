package com.project.Instargram.kotlin.src.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityLoginBinding
import com.project.Instargram.kotlin.src.main.MainActivity


class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()



        binding.edittextId.setOnClickListener {
//            Log.d(TAG, "onResume: clicked")
//            binding.ivLogo.setScaleY(0.4.toFloat())
//            binding.ivLogo.setScaleX(0.4.toFloat())
//            val layoutParams = ConstraintLayout.LayoutParams(100, 100)
//            binding.ivLogo.setLayoutParams(layoutParams)
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}