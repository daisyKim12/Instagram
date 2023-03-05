package com.project.Instargram.kotlin.src.main.reels

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseFragment
import com.project.Instargram.kotlin.databinding.FragmentReelsBinding
import com.project.Instargram.kotlin.src.main.TempPageLists
import com.project.Instargram.kotlin.src.main.reels.adapter.ReelsAdapter
import java.util.*

class ReelsFragment: BaseFragment<FragmentReelsBinding>(FragmentReelsBinding::bind, R.layout.fragment_reels) {
    private var viewPager2: ViewPager2? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val adapter = ReelsAdapter(requireContext(), TempPageLists.reelsAdpSlides)
        viewPager2 = binding.vpReels
        viewPager2?.adapter = adapter
    }
}