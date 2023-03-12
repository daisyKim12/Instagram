package com.project.Instargram.kotlin.src.main.post

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityPostStyleBinding
import com.project.Instargram.kotlin.src.main.post.adapter.FilterAdapter
import com.project.Instargram.kotlin.src.main.post.adapter.PostGalleryAdapter
import com.project.Instargram.kotlin.src.main.post.adapter.PostGalleryMultiAdapter
import com.project.Instargram.kotlin.src.main.post.adapter.RetouchAdapter
import com.project.Instargram.kotlin.src.main.post.model.ImagesGallery
import com.project.Instargram.kotlin.src.main.post.model.PageLists

class PostStyleActivity: BaseActivity<ActivityPostStyleBinding>(ActivityPostStyleBinding::inflate) {

    private lateinit var imageToPost: String
    private val KEY_SINGLE = "sigle_image"
    private val ISITSINGLE = "is_it_single_image"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageToPost = intent.getStringExtra(KEY_SINGLE).toString()

        loadStyleRecyclerView(0)

        Glide.with(this).load(imageToPost).into(binding.imgResize)

    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }
        binding.tbNext.setOnClickListener {
            val intent= Intent(this, PostFinalActivity::class.java)
            intent.putExtra(ISITSINGLE, true)
            intent.putExtra(KEY_SINGLE, imageToPost)
            startActivity(intent)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab != null) {
                    loadStyleRecyclerView(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun loadStyleRecyclerView(id: Int) {
        binding.rvStyle.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvStyle.layoutManager = linearLayoutManager

        if(id == 0) {
            val adapter = FilterAdapter(this, PageLists.filterSlide, imageToPost)
            adapter.notifyDataSetChanged()
            binding.rvStyle.adapter = adapter


        }
        if(id == 1) {
            val adapter = RetouchAdapter(PageLists.retouchSlide)
            adapter.notifyDataSetChanged()
            binding.rvStyle.adapter = adapter
        }

    }

}