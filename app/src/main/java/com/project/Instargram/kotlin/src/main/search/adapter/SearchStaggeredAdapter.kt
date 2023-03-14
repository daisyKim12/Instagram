package com.project.Instargram.kotlin.src.main.search.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvRectangleItemBinding
import com.project.Instargram.kotlin.databinding.RvSquareItemBinding
import com.project.Instargram.kotlin.src.main.search.model.GetWithoutSearchResponse

class SearchStaggeredAdapter(private val context: Context, private val response: GetWithoutSearchResponse,
                             private val rectangle: List<String>, private val photoListener: PhotoListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var squarePosition = 0
    private var rectanglePosition = 0
    private val result = response.result


    inner class SquareViewHolder(private val binding : RvSquareItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindItem(url: String) {
            Glide.with(context).load(url).into(binding.imgPost)
        }
        fun bindClickable(postIdx: Int){
            binding.imgPost.setOnClickListener {
                photoListener.onPhotoClick(postIdx)
            }
        }
    }

    inner class RectangleViewHolder(private val binding: RvRectangleItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(url: String) {
            Glide.with(context).load(url).into(binding.imgPost)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 0) {
            return RectangleViewHolder(
                RvRectangleItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return SquareViewHolder(
                RvSquareItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position % 13 == 0) {
            (holder as RectangleViewHolder).bindItem(rectangle[rectanglePosition++])
        } else {
            (holder as SquareViewHolder).bindItem(result[squarePosition].fileURLList[0])
            var postIdx = result[squarePosition].postIdx
            holder.bindClickable(postIdx)
            if(squarePosition < result.size-1){
                squarePosition++
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position % 13 == 0) {
            //rect
            return 0
        } else {
            //square
            return 1
        }
    }

    override fun getItemCount(): Int {
        return result.size + rectangle.size
    }
    public interface PhotoListener {
        fun onPhotoClick(postIdx: Int)
    }
}