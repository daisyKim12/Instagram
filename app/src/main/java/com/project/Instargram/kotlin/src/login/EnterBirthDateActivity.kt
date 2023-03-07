package com.project.Instargram.kotlin.src.login

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContentProviderCompat.requireContext
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterBirthDateBinding
import com.project.Instargram.kotlin.databinding.DialogDatePickerBinding

class EnterBirthDateActivity: BaseActivity<ActivityEnterBirthDateBinding>(ActivityEnterBirthDateBinding::inflate) {

    private val KEY_SEND = "birth_date"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }

        binding.btnDatePicker.setOnClickListener {
            openDatePickerDialog()
        }

        binding.btnNext.setOnClickListener {

        }
    }

    private fun openDatePickerDialog() {
        val dialog = Dialog(this)
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true)
        //Mention the name of the layout of your custom dialog.
        val binding = DialogDatePickerBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)


        binding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnSet.setOnClickListener {
            updateBirthDate()
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun updateBirthDate() {

    }


}