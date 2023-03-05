package com.project.Instargram.kotlin.src.main.search

import android.os.Bundle
import android.view.View
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
}