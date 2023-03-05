package com.project.Instargram.kotlin.src.main.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseFragment
import com.project.Instargram.kotlin.databinding.BottomSheetHomeBinding
import com.project.Instargram.kotlin.databinding.FragmentHomeBinding
import com.project.Instargram.kotlin.src.main.home.adpater.PostAdapter
import com.project.Instargram.kotlin.src.main.home.adpater.StoryAdapter
import com.project.Instargram.kotlin.src.main.TempPageLists

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpStoryRecyclerView()
        setUpPostRecyclerView()
    }

    private fun setUpStoryRecyclerView() {
        binding.rvStory.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvStory.layoutManager = linearLayoutManager
        val adapter = StoryAdapter(requireContext(), TempPageLists.storyAdpSlides)
        adapter.notifyDataSetChanged()
        binding.rvStory.adapter = adapter
    }

    private fun setUpPostRecyclerView() {
        binding.rvPost.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvPost.layoutManager = linearLayoutManager
        val adapter = PostAdapter(requireContext(), TempPageLists.postAdpSlides)
        adapter.notifyDataSetChanged()
        binding.rvPost.adapter = adapter
    }


    private fun showBottomSheetAdv() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val binding: BottomSheetHomeBinding = BottomSheetHomeBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(binding.root)
    }



}