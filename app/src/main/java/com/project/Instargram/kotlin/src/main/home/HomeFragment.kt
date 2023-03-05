package com.project.Instargram.kotlin.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseFragment
import com.project.Instargram.kotlin.databinding.FragmentHomeBinding
import com.project.Instargram.kotlin.src.main.home.models.PostSignUpRequest
import com.project.Instargram.kotlin.src.main.home.models.SignUpResponse
import com.project.Instargram.kotlin.src.main.home.models.UserResponse

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}