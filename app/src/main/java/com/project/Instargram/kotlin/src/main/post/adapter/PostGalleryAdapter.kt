package com.project.Instargram.kotlin.src.main.post.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.databinding.RvSquareGalleryItemBinding
import com.project.Instargram.kotlin.src.main.post.model.Gallery

class PostGalleryAdapter(private val context: Context, private val gallery: List<Gallery>, private val photoListener: PhotoListener)
    : RecyclerView.Adapter<PostGalleryAdapter.GalleryViewHolder>()  {

    inner class GalleryViewHolder(private val binding : RvSquareGalleryItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindItem(url: String) {
            Glide.with(context).load(url).into(binding.imgPost)
            binding.imgMoreThanOne.visibility = View.INVISIBLE
            binding.txtMoreThanOne.visibility = View.INVISIBLE

            binding.blurred.visibility = View.INVISIBLE
        }

        fun bindClickable(gallery: Gallery) {
            binding.imgPost.setOnClickListener {
                if(gallery.clicked == false) {
                    photoListener.onPhotoClick(gallery.url)
                    binding.blurred.visibility = View.VISIBLE
                    gallery.clicked = true
                } else {
                    photoListener.onPhotoClick(gallery.url)
                    binding.blurred.visibility = View.INVISIBLE
                    gallery.clicked = false
                }

                Log.d(TAG, "bindClickable: " + gallery.clicked)

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
        Log.d(TAG, "onBindViewHolder: " + gallery[position])
        holder.bindItem(gallery[position].url)
        holder.bindClickable(gallery[position])

    }

    override fun getItemCount(): Int {
        return gallery.size
    }

    public interface PhotoListener{
        fun onPhotoClick(path: String)
    }
}