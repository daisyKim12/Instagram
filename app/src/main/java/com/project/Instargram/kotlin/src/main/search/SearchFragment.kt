package com.project.Instargram.kotlin.src.main.search

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseFragment
import com.project.Instargram.kotlin.databinding.FragmentSearchBinding
import com.project.Instargram.kotlin.src.main.TempPageLists
import com.project.Instargram.kotlin.src.main.page.model.profile.PageService
import com.project.Instargram.kotlin.src.main.search.adapter.SearchStaggeredAdapter
import com.project.Instargram.kotlin.src.main.search.model.GetWithoutSearchResponse


class SearchFragment: BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search),SearchInterface {

    private val KEY_USERID = "userIdx"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeEditText(false)

        binding.edittextSearch.setOnFocusChangeListener { view, b -> changeEditText(b)}
        binding.tbBack.setOnClickListener {
            changeEditText(false)
        }

        val userIdx = getIntegerValue(KEY_USERID)!!
        Log.d(ContentValues.TAG, "onViewCreated: userIdx -> " + userIdx )
        SearchService(this).tryGetWitoutSearch(userIdx, 1)

        //setUpStaggeredRecyclerView()
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

    override fun onGetWithoutSearchSuccess(response: GetWithoutSearchResponse) {
        Log.d(TAG, "onGetWithoutSearchSuccess: " + response)
        setUpStaggeredRecyclerView(response)
    }

    override fun onGetWithoutSearchFailure(message: String) {
        Log.d(TAG, "onGetWithoutSearchFailure: " + message)
    }

    private fun setUpSearchRecyclerView() {
        binding.rvSearch.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvSearch.layoutManager = linearLayoutManager
        //val adapter = SearchAdapter(requireContext(), ??)
        //adapter.notifyDataSetChanged()
        //binding.rvSearch.adapter = adapter
    }

    private fun setUpStaggeredRecyclerView(response: GetWithoutSearchResponse) {

        //4칸짜리 광고를 포기하면 이정도로 가능
        // (view.layoutParams as ConstraintLayout.LayoutParams).dimensionRatio = "16:9" ratio 변화

        binding.rvSearch.setHasFixedSize(true)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rvSearch.layoutManager = staggeredGridLayoutManager
        val adapter = SearchStaggeredAdapter(requireContext(), response, TempPageLists.rectangleAdpSlides)
        adapter.notifyDataSetChanged()
        binding.rvSearch.adapter = adapter
    }
}