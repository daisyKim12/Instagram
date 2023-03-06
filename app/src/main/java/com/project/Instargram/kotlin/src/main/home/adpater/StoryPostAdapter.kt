package com.project.Instargram.kotlin.src.main.home.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvHomePostItemBinding
import com.project.Instargram.kotlin.databinding.RvHomeStoryLayoutBinding
import com.project.Instargram.kotlin.src.main.TempPageLists
import com.project.Instargram.kotlin.src.main.home.models.Post
import com.project.Instargram.kotlin.src.main.home.models.Story

class StoryPostAdapter(private val context: Context, private val post: List<Post>, private val story: List<Story>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class StoryViewHolder(private val binding : RvHomeStoryLayoutBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindItem(story: List<Story>) {
            binding.rvStory.setHasFixedSize(true)
            val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.rvStory.layoutManager = linearLayoutManager
            val adapter = StoryAdapter(context, story)
            adapter.notifyDataSetChanged()
            binding.rvStory.adapter = adapter
        }
    }

    inner class PostViewHolder(private val binding: RvHomePostItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(post: Post) {

            setUpViewPager(context, post.postUrlList)

            Glide.with(context).load(post.tumbnailUrl).into(binding.imgThumbnail)
            binding.txtName.text = post.name
            //Glide.with(context).load(post.postUrl).into(binding.rvPost)
            binding.txtLike.text = "좋아요 " + post.like.toString() + "개"
            binding.txtDetail.text = post.name + " " + post.detailUrl
            binding.txtComment.text = "댓글" + post.commentNum.toString() + "개 보기"
            //binding.txtTime.text = ??

            //TODO("add click listener")
        }

        fun setUpViewPager(context: Context, postUrlList: List<String>){
            val adapter = PostImageAdapter(context, postUrlList)
            val viewPager2 = binding.vpPost
            viewPager2?.adapter = adapter
            //viewPager2?.registerOnPageChangeCallback(pager2Callback)
            binding.dotsIndicator.setViewPager2(viewPager2!!)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 0) {
            return StoryViewHolder(
                RvHomeStoryLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return PostViewHolder(
                RvHomePostItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position == 0) {
            (holder as StoryViewHolder).bindItem(story)
        } else {
            (holder as PostViewHolder).bindItem(post[position-1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position == 0) {
            return 0
        } else {
            return 1
        }
    }

    override fun getItemCount(): Int {
        return post.size + 1
    }

}

