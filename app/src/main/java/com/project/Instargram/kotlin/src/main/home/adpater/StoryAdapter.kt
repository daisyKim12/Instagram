package com.project.Instargram.kotlin.src.main.home.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.databinding.RvHomePostItemBinding
import com.project.Instargram.kotlin.databinding.RvHomeStoryItemBinding
import com.project.Instargram.kotlin.src.main.home.models.Story

class StoryAdapter(private val context: Context, private val story: List<Story>)
    : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    private val KEY_MY_IMAGE = "image_path"

    inner class StoryViewHolder(private val binding: RvHomeStoryItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(story: Story) {
            Glide.with(context).load(story.tumbnailUrl).into(binding.imgThumbnail)
            binding.txtName.text = story.name
            if(story.clicked == false){
                binding.btnAdd.visibility = View.VISIBLE
            } else {
                binding.btnAdd.visibility = View.INVISIBLE
            }

            //TODO("add long click listener")
            //animation and name text color change
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder(
            RvHomeStoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        if(position == 0) {
            val firstStory = story[0]
            firstStory.tumbnailUrl = getStringValue(KEY_MY_IMAGE)!!
            holder.bindItem(story[position])
        } else {
            holder.bindItem(story[position])
        }
    }

    override fun getItemCount(): Int {
        return story.size
    }

    fun getStringValue(KEY: String): String? {
        return ApplicationClass.sSharedPreferences.getString(KEY, "error")
    }
}