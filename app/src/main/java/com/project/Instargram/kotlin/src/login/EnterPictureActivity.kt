package com.project.Instargram.kotlin.src.login

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterPictureBinding
import com.project.Instargram.kotlin.databinding.BottomSheetLoginGalleryBinding

class EnterPictureActivity : BaseActivity<ActivityEnterPictureBinding>(ActivityEnterPictureBinding::inflate) {

    private val KEY_SEND = "profile_picture"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }

        binding.btnAddPicture.setOnClickListener {
            showBottomSheet()
        }

        binding.btnSkip.setOnClickListener {

        }

    }

    private fun showBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val binding: BottomSheetLoginGalleryBinding = BottomSheetLoginGalleryBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(binding.root)

        binding.btnExit.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        binding.btnGallery.setOnClickListener {

        }
        binding.btnCamera.setOnClickListener {

        }

        bottomSheetDialog.show()
    }
}