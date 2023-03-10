package com.project.Instargram.kotlin.src.login.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.showProgress
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityEnterPictureBinding
import com.project.Instargram.kotlin.databinding.BottomSheetLoginGalleryBinding
import com.project.Instargram.kotlin.src.splash.SplashNewAccountActivity


class EnterPictureActivity : BaseActivity<ActivityEnterPictureBinding>(ActivityEnterPictureBinding::inflate) {


    private val MY_GALLERY_PERMISSION_CODE = 101
    private val MY_CAMERA_PERMISSION_CODE = 102

    private val KEY_SEND = "image_path"

    private lateinit var handler: Handler
    private var imageSelected = false
    private lateinit var photoUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindProgressButton(binding.btnAddPicture)

        handler = Handler(Looper.getMainLooper())

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
                val absolutePath = absolutelyPath(photoUri)
                if(absolutePath != null) {
                    saveFromEditText(KEY_SEND, absolutePath)
                }
                waitAndMoveToNextActivity()
            }
        }

        binding.btnSkip.setOnClickListener {
            if(imageSelected == false){
               //완료, 기본 이미지와
               waitAndMoveToNextActivity()
            } else {
                openGallery()
            }
        }

    }

    fun absolutelyPath(path: Uri): String? {

        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = contentResolver.query(path, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result
    }


    fun waitAndMoveToNextActivity() {
        //button progress
        Thread() {
            handler.post(Runnable {
                binding.btnAddPicture.showProgress{ progressColor = Color.WHITE }
            })
            Thread.sleep(5000)
            //Log.d(TAG, "waitAndMoveToNext: "+ ???)
            val intent = Intent(this, SplashNewAccountActivity::class.java)
            startActivity(intent)
        }.start()
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
                if(data != null) {
                    binding.circleImageView.setImageURI(data.data)
                    photoUri = data.data!!
                    Log.d(TAG, "onActivityResult: " + data.data)
                }
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