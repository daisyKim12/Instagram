package com.project.Instargram.kotlin.src.main.search

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseFragment
import com.project.Instargram.kotlin.databinding.FragmentSearchBinding


class SearchFragment: BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeEditText(false)

        binding.edittextSearch.setOnFocusChangeListener { view, b -> changeEditText(b)}
        binding.tbBack.setOnClickListener {
            changeEditText(false)
        }

    }

    private fun changeEditText(b: Boolean) {
        if(b == true){
            val params = binding.edittextSearch.getLayoutParams()
            params.width = 880
            binding.edittextSearch.setLayoutParams(params)
            binding.tbBack.visibility = View.VISIBLE

        } else {
            val params = binding.edittextSearch.getLayoutParams()
            params.width = 950
            binding.edittextSearch.setLayoutParams(params)
            binding.tbBack.visibility = View.INVISIBLE
        }
    }

    private fun setUpSearchRecyclerView() {
        binding.rvSearch.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvSearch.layoutManager = linearLayoutManager
        //val adapter = SearchAdapter(requireContext(), ??)
        //adapter.notifyDataSetChanged()
        //binding.rvSearch.adapter = adapter
    }

    private fun setUpStaggeredRecyclerView() {

        //4칸짜리 광고를 포기하면 이정도로 가능
        // (view.layoutParams as ConstraintLayout.LayoutParams).dimensionRatio = "16:9" ratio 변화
        binding.rvSearch.setHasFixedSize(true)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rvSearch.layoutManager = staggeredGridLayoutManager
        //val adapter = StaggeredAdapter(requireContext(), ??)
        //adapter.notifyDataSetChanged()
        //inding.rvSearch.adapter = adapter
    }
}