package com.project.Instargram.kotlin.src.login

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityLoginBinding


class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val layoutParams = LinearLayout.LayoutParams(100, 100)
//        binding.ivLogo.setLayoutParams(layoutParams)

    }

    override fun onResume() {
        super.onResume()


//        KeyboardVisibilityEvent.setEventListener(
//            getActivity(),
//            object : KeyboardVisibilityEventListener() {
//                fun onVisibilityChanged(isOpen: Boolean) {
//                    // Ah... at last. do your thing :)
//                }
//            })
    }
}