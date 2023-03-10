package com.project.Instargram.kotlin.src.main.page.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseFragment
import com.project.Instargram.kotlin.databinding.FragmentMyPostBinding
import com.project.Instargram.kotlin.src.main.TempPageLists
import com.project.Instargram.kotlin.src.main.page.adapter.MyPostAdapter

class MyPostFragment : BaseFragment<FragmentMyPostBinding>(FragmentMyPostBinding::bind, R.layout.fragment_my_post) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMyPostRecyclerView()
    }

    private fun setUpMyPostRecyclerView() {

        //4칸짜리 광고를 포기하면 이정도로 가능
        // (view.layoutParams as ConstraintLayout.LayoutParams).dimensionRatio = "16:9" ratio 변화

        binding.rvMyPost.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false )
        binding.rvMyPost.layoutManager = gridLayoutManager
        val adapter = MyPostAdapter(requireContext(), TempPageLists.squareAdpSlides)
        adapter.notifyDataSetChanged()
        binding.rvMyPost.adapter = adapter
    }
}
