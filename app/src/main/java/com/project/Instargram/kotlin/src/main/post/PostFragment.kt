package com.project.Instargram.kotlin.src.main.post

import android.os.Bundle
import android.view.View
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseFragment
import com.project.Instargram.kotlin.databinding.FragmentPostBinding

class PostFragment: BaseFragment<FragmentPostBinding>(FragmentPostBinding::bind, R.layout.fragment_post) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}