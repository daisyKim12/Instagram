package com.project.Instargram.kotlin.src.main.myPage

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseFragment
import com.project.Instargram.kotlin.databinding.FragmentMyPageBinding
import com.project.Instargram.kotlin.src.main.myPage.adapter.TabFragmentAdapter

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapterForFragment()
    }

    private fun setAdapterForFragment() {
        val adapter = TabFragmentAdapter(parentFragmentManager, lifecycle)

//        //add tab in tab layout
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("홈"))
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("이벤트"))
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("무비톡"))

        binding.container.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab != null) {
                    binding.container.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.container.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }
}