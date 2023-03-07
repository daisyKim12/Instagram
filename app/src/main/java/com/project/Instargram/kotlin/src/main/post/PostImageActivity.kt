package com.project.Instargram.kotlin.src.main.post

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.davemorrissey.labs.subscaleview.ImageSource
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityPostImageBinding
import com.project.Instargram.kotlin.src.main.TempPageLists
import com.project.Instargram.kotlin.src.main.myPage.adapter.MyPostAdapter
import com.project.Instargram.kotlin.src.main.post.adapter.PostGalleryAdapter

class PostImageActivity : BaseActivity<ActivityPostImageBinding>(ActivityPostImageBinding::inflate){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpMyPostRecyclerView()

        binding.imgResize.setImage(ImageSource.uri("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/White_and_yellow_flower.JPG/330px-White_and_yellow_flower.JPG"))
    }

    private fun setUpMyPostRecyclerView() {
        binding.rvGallery.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false )
        binding.rvGallery.layoutManager = gridLayoutManager
        val adapter = PostGalleryAdapter(this, TempPageLists.squareAdpSlides)
        adapter.notifyDataSetChanged()
        binding.rvGallery.adapter = adapter
    }

}