package com.project.Instargram.kotlin.src.main.home.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.Instargram.kotlin.databinding.RvHomePostItemBinding
import com.project.Instargram.kotlin.databinding.RvHomeStoryLayoutBinding
import com.project.Instargram.kotlin.src.main.home.models.Story
import com.project.Instargram.kotlin.src.main.home.models.getFeed.GetPostResponse

class StoryPostAdapter(private val context: Context, private val getPostResponse: GetPostResponse,
                       //private val post: List<Post>,
                       private val story: List<Story>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val result = getPostResponse.result
    val feedList = result.feedList
    val followInfoList = result.followInfoList

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

//    inner class PostViewHolder(private val binding: RvHomePostItemBinding)
//        : RecyclerView.ViewHolder(binding.root) {
//
//        fun bindItem(feed: Feed) {
//
//            setUpViewPager(context, feed.postFileURLList)
//
//            Glide.with(context).load(feed.authorProfileImgURL).into(binding.imgThumbnail)
//            binding.txtName.text = feed.authorNickName
//            binding.txtLike.text = "좋아요 " + feed.likeNumber.toString() + "개"
//            binding.txtDetail.text = feed.authorNickName + " " + feed.postText
//            //binding.txtComment.text = "댓글" + feed.commentNum.toString() + "개 보기"
//            binding.txtTime.text = feed.since.toString()
//
//        }
//
//        fun setUpViewPager(context: Context, postUrlList: List<String>){
//            val adapter = PostImageAdapter(context, postUrlList)
//            val viewPager2 = binding.vpPost
//            viewPager2?.adapter = adapter
//            //viewPager2?.registerOnPageChangeCallback(pager2Callback)
//            binding.dotsIndicator.setViewPager2(viewPager2!!)
//        }
//
//    }

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
            return PostViewHolder( context,
                RvHomePostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position == 0) {
            (holder as StoryViewHolder).bindItem(story)
        } else {
            (holder as PostViewHolder).bindItem(feedList[position - 1])
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
        return feedList.size + 1
    }

}

