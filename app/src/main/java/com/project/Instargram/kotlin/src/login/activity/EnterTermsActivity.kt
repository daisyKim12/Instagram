package com.project.Instargram.kotlin.src.login.activity

import android.content.Intent
import android.os.Bundle
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterTermsBinding

class EnterTermsActivity : BaseActivity<ActivityEnterTermsBinding>(ActivityEnterTermsBinding::inflate) {

    private val KEY_SEND = "terms"
    private var term1 = false
    private var term2 = false
    private var term3 = false
    private var btnAllClicked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }

        binding.btnAll.setOnClickListener {
            if(btnAllClicked == false){
                binding.btnTerm1.isChecked = true
                binding.btnTerm2.isChecked = true
                binding.btnTerm3.isChecked = true
                binding.btnAll.text = "모두 선택 취소"
                btnAllClicked = true
            } else {
                binding.btnTerm1.isChecked = false
                binding.btnTerm2.isChecked = false
                binding.btnTerm3.isChecked = false
                binding.btnAll.text = "모두 선택"
                btnAllClicked = false
            }
        }

        binding.btnTerm1.setOnClickListener {
            term1 = true
        }
        binding.btnTerm2.setOnClickListener {
            term2 = true
        }
        binding.btnTerm3.setOnClickListener {
            term3 = true
        }

        if(term1 == true && term2 == true && term3 == true){
            binding.btnNext.isClickable = true
        } else {
            binding.btnNext.isClickable = false
        }

        binding.btnNext.setOnClickListener {
            val data = true
            saveBoolean(KEY_SEND, data)
            startActivity(Intent(this, EnterPictureActivity::class.java))
        }

    }
}