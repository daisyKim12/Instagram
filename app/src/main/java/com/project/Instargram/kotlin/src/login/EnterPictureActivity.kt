package com.project.Instargram.kotlin.src.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterPictureBinding
import com.project.Instargram.kotlin.databinding.BottomSheetLoginGalleryBinding

class EnterPictureActivity : BaseActivity<ActivityEnterPictureBinding>(ActivityEnterPictureBinding::inflate) {

    private val MY_GALLERY_PERMISSION_CODE = 101
    private val MY_CAMERA_PERMISSION_CODE = 102

    private val KEY_SEND = "profile_picture"

    private var imageSelected = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permission_camera: Int? = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        //check for permission
        if(permission_camera != PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
        }

    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }

        binding.btnAddPicture.setOnClickListener {
            if(imageSelected == false){
                showBottomSheet()
            } else {
                //완료, 고른 이미지와
            }
        }

        binding.btnSkip.setOnClickListener {
            if(imageSelected == false){
               //완료, 기본 이미지와
            } else {
                openGallery()
            }
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
            openGallery()
            bottomSheetDialog.dismiss()

        }
        binding.btnCamera.setOnClickListener {
            openCamera()
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, MY_CAMERA_PERMISSION_CODE)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, MY_GALLERY_PERMISSION_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            if(requestCode == MY_GALLERY_PERMISSION_CODE) {
                binding.circleImageView.setImageURI(data?.data)
                changeLayout()
            }
        }

        if(resultCode == RESULT_OK) {
            if(requestCode == MY_CAMERA_PERMISSION_CODE) {
                //binding.circleImageView.setImageURI(data?.data)
                val bitmap: Bitmap = data?.extras?.get("data") as Bitmap
                Log.d(TAG, "onActivityResult: "+ bitmap)
                binding.circleImageView.setImageBitmap(bitmap)
                changeLayout()
            }
        }
    }

    private fun changeLayout() {
        binding.txtDetail1.visibility = View.VISIBLE
        binding.txtDetail2.visibility = View.VISIBLE

        binding.btnChangeImage.visibility = View.VISIBLE
        binding.btnChangeImage.isClickable = true

        binding.cv.visibility = View.VISIBLE

        binding.btnAddPicture.text = "완료"
        binding.btnSkip.text = "사진 변경"

        imageSelected = true
    }
}