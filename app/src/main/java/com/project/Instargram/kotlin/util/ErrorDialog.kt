package com.project.Instargram.kotlin.util

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.project.Instargram.kotlin.databinding.DialogErrorBinding
import com.project.Instargram.kotlin.databinding.DialogLoadingBinding

class ErrorDialog(context: Context, val title: String, val message: String) : Dialog(context) {
    private lateinit var binding: DialogErrorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCanceledOnTouchOutside(false)
        setCancelable(true)
        window!!.setBackgroundDrawable(ColorDrawable())
        window!!.setDimAmount(0.2f)

        binding.txtTitle.text = title
        binding.txtMessage.text = message
        binding.btnDismiss.setOnClickListener {
            dismiss()
        }
    }

    override fun show() {
        if(!this.isShowing) super.show()
    }
}