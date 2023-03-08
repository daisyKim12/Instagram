package com.project.Instargram.kotlin.src.main.post.adapter

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvSquareGalleryItemBinding

class PostGalleryMultiAdapter (private val context: Context, private val gallery: List<String>, private val photoListener: PhotoListener)
    : RecyclerView.Adapter<PostGalleryMultiAdapter.GalleryViewHolder>()  {

    inner class GalleryViewHolder(private val binding : RvSquareGalleryItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindItem(url: String) {
            Glide.with(context).load(url).into(binding.imgPost)
            binding.imgMoreThanOne.visibility = View.VISIBLE
            binding.blurred.visibility = View.INVISIBLE
            //binding.imgMoreThanOne.visibility = View.INVISIBLE
        }

        fun bindClickable(image: String) {
            binding.imgPost.setOnClickListener {
                photoListener.onPhotoClick(image)
                binding.blurred.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryViewHolder {
        return GalleryViewHolder(
            RvSquareGalleryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        Log.d(ContentValues.TAG, "onBindViewHolder: " + gallery[position])
        holder.bindItem(gallery[position])
        holder.bindClickable(gallery[position])

    }

    override fun getItemCount(): Int {
        return gallery.size
    }

    public interface PhotoListener{
        fun onPhotoClick(path: String)
    }
}