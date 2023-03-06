package com.project.Instargram.kotlin.src.main.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvRectangleItemBinding
import com.project.Instargram.kotlin.databinding.RvSquareItemBinding

class SearchStaggeredAdapter(private val context: Context, private val square: List<String>, private val rectangle: List<String>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var squarePosition = 0
    private var rectanglePosition = 0


    inner class SquareViewHolder(private val binding : RvSquareItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindItem(url: String) {
            Glide.with(context).load(url).into(binding.imgPost)
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
            (holder as SquareViewHolder).bindItem(square[squarePosition])
            if(squarePosition < square.size-1){
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
        return square.size + rectangle.size
    }
}