package com.project.Instargram.kotlin.src.login.activity

import android.content.Intent
import android.os.Bundle
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterTermsBinding

class EnterTermsActivity : BaseActivity<ActivityEnterTermsBinding>(ActivityEnterTermsBinding::inflate) {

    private val KEY_SEND = "terms"
    private var term1: Boolean = false
    private var term2: Boolean = false
    private var term3: Boolean = false
    private var btnAllClicked: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        term1 = false
        term2 = false
        term3 = false
        btnAllClicked = false
        checkAndEnableBtn()
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
                term1 = true
                term1 = true
                term1 = true
                checkAndEnableBtn()
                btnAllClicked = true
            } else {
                binding.btnTerm1.isChecked = false
                binding.btnTerm2.isChecked = false
                binding.btnTerm3.isChecked = false
                binding.btnAll.text = "모두 선택"
                term1 = false
                term1 = false
                term1 = false
                checkAndEnableBtn()
                btnAllClicked = false
            }
        }

        binding.btnTerm1.setOnClickListener {
            term1 = true
            checkAndEnableBtn()
        }
        binding.btnTerm2.setOnClickListener {
            term2 = true
            checkAndEnableBtn()
        }
        binding.btnTerm3.setOnClickListener {
            term3 = true
            checkAndEnableBtn()
        }



        binding.btnNext.setOnClickListener {
            val data = true
            startActivity(Intent(this, EnterPictureActivity::class.java))
        }

    }

    private fun checkAndEnableBtn() {
        if(term1 == true && term2 == true && term3 == true){
            binding.btnNext.isClickable = true
        } else {
            binding.btnNext.isClickable = false
        }
    }
}