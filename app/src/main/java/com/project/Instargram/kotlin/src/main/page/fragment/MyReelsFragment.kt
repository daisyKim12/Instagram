package com.project.Instargram.kotlin.src.main.page.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseFragment
import com.project.Instargram.kotlin.databinding.FragmentMyReelsBinding
import com.project.Instargram.kotlin.src.main.TempPageLists
import com.project.Instargram.kotlin.src.main.page.adapter.MyReelsAdapter

class MyReelsFragment: BaseFragment<FragmentMyReelsBinding>(FragmentMyReelsBinding::bind, R.layout.fragment_my_reels){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setUpMyPostRecyclerView()
    }

    private fun setUpMyPostRecyclerView() {
        binding.rvMyReels.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false )
        binding.rvMyReels.layoutManager = gridLayoutManager
        val adapter = MyReelsAdapter(requireContext(), TempPageLists.rectangleAdpSlides)
        adapter.notifyDataSetChanged()
        binding.rvMyReels.adapter = adapter
    }
}
