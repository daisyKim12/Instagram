package com.project.Instargram.kotlin.src.main.post

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityPostImageBinding
import com.project.Instargram.kotlin.src.main.post.model.ImagesGallery
import com.project.Instargram.kotlin.src.main.post.adapter.PostGalleryAdapter
import com.project.Instargram.kotlin.src.main.post.adapter.PostGalleryMultiAdapter

class PostImageActivity : BaseActivity<ActivityPostImageBinding>(ActivityPostImageBinding::inflate){

    private val MY_READ_PERMISSION_CODE = 101
    private lateinit var images: List<String>
    private var clickedImages: ArrayList<String> = arrayListOf()
    private var clickedsingleImage: String = ""
    private var btnMultiClicked = false

    private val KEY_SINGLE = "sigle_image"
    private val KEY_MULTI = "multi_image"
    private val BOOLEAN_MULTI = "is_it_a_multi_image"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //        binding.imgResize.setImage(ImageSource.uri("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/White_and_yellow_flower.JPG/330px-White_and_yellow_flower.JPG"))
        // setUpMyPostRecyclerView()
        val permission_read: Int? = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        val permission_write: Int? = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        //check for permission
        if(permission_read != PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), MY_READ_PERMISSION_CODE)
        } else {
            loadGallery(false)
        }
    }


    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }
        binding.tbNext.setOnClickListener {
            var intent: Intent
            if(btnMultiClicked == false) {
                intent= Intent(this, PostStyleActivity::class.java)
                intent.putExtra(BOOLEAN_MULTI, btnMultiClicked)
                intent.putExtra(KEY_SINGLE, clickedsingleImage)
                startActivity(intent)
            }
            else {
                intent= Intent(this, PostStyleRvActivity::class.java)
                intent.putExtra(BOOLEAN_MULTI, btnMultiClicked)
                intent.putStringArrayListExtra(KEY_MULTI, clickedImages)
                startActivity(intent)
            }
        }
        binding.galleryMulti.setOnClickListener {
            binding.galleryMulti.isClickable = false
            binding.galleryMultiClicked.isClickable = true
            binding.galleryMulti.visibility = View.INVISIBLE
            binding.galleryMultiClicked.visibility = View.VISIBLE
            loadGallery(true)
            btnMultiClicked = true

        }

        binding.galleryMultiClicked.setOnClickListener {
            binding.galleryMulti.isClickable = true
            binding.galleryMultiClicked.isClickable = false
            binding.galleryMulti.visibility = View.VISIBLE
            binding.galleryMultiClicked.visibility = View.INVISIBLE
            loadGallery(false)
            btnMultiClicked = false

        }

    }

    private fun loadGallery(multi: Boolean) {
        binding.rvGallery.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)
        images = ImagesGallery.listOfImages(this)
        binding.rvGallery.layoutManager = gridLayoutManager

//        Log.d(TAG, "loadGallery: "+ images[0])
        val adapterSingle = PostGalleryAdapter(this, images, object : PostGalleryAdapter.PhotoListener {
            override fun onPhotoClick(path: String) {

                clickedsingleImage = path
                if(path != null){
                    Glide.with(this@PostImageActivity).load(clickedsingleImage).into(binding.imgResize)
                }

            }
        })

        val adapterMulti = PostGalleryMultiAdapter(this, images, object : PostGalleryMultiAdapter.PhotoListener {
            override fun onPhotoClick(path: String) {

                clickedImages.add(path)

                if(path != null){
                    Glide.with(this@PostImageActivity).load(path).into(binding.imgResize)
                }
            }
        })
        //adapter.notifyDataSetChanged()

        if(multi != true) {
            binding.rvGallery.adapter = adapterSingle
        } else {
            binding.rvGallery.adapter = adapterMulti
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if(requestCode == MY_READ_PERMISSION_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d(TAG, "onRequestPermissionsResult: granted")
                loadGallery(false)
            } else {
                Log.d(TAG, "onRequestPermissionsResult: denied")
            }
        }
    }

//    private fun setUpMyPostRecyclerView() {
//        binding.rvGallery.setHasFixedSize(true)
//        val gridLayoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false )
//        binding.rvGallery.layoutManager = gridLayoutManager
//        val adapter = PostGalleryAdapter(this, TempPageLists.squareAdpSlides)
//        adapter.notifyDataSetChanged()
//        binding.rvGallery.adapter = adapter
//    }

}