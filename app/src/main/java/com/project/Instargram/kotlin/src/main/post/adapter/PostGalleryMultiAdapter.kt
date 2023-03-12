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
import com.project.Instargram.kotlin.src.main.post.model.Gallery

class PostGalleryMultiAdapter (private val context: Context, private val gallery: List<Gallery>, private val photoListener: PhotoListener)
    : RecyclerView.Adapter<PostGalleryMultiAdapter.GalleryViewHolder>()  {

    private var clickedNumber = 0

    inner class GalleryViewHolder(private val binding : RvSquareGalleryItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindItem(url: String) {
            Glide.with(context).load(url).into(binding.imgPost)
            binding.imgMoreThanOne.visibility = View.VISIBLE
            binding.txtMoreThanOne.visibility = View.INVISIBLE
            binding.blurred.visibility = View.INVISIBLE
        }

        fun bindClickable(gallery: Gallery) {
            binding.imgPost.setOnClickListener {
                if(gallery.clicked == false) {
                    photoListener.onPhotoClick(gallery.url)
                    binding.blurred.visibility = View.VISIBLE
                    gallery.number  = ++clickedNumber
                    binding.txtMoreThanOne.text = gallery.number.toString()
                    binding.txtMoreThanOne.visibility = View.VISIBLE

                    binding.imgMoreThanOne.setImageResource(com.project.Instargram.kotlin.R.color.blueForGallery)
                    gallery.clicked = true
                } else {
                    photoListener.onPhotoClick(gallery.url)
                    binding.blurred.visibility = View.INVISIBLE
                    gallery.number  = --clickedNumber
                    binding.txtMoreThanOne.visibility = View.INVISIBLE

                    binding.imgMoreThanOne.setImageResource(com.project.Instargram.kotlin.R.color.grayForGallery)
                    gallery.clicked = false
                }

                Log.d(ContentValues.TAG, "bindClickable: " + gallery.clicked)

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