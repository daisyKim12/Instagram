package com.project.Instargram.kotlin.src.main.page

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityUserPageBinding
import com.project.Instargram.kotlin.src.main.TempPageLists
import com.project.Instargram.kotlin.src.main.page.adapter.StoryHighlightAdapter
import com.project.Instargram.kotlin.src.main.page.adapter.TabFragmentAdapter

class UserPageActivity: BaseActivity<ActivityUserPageBinding>(ActivityUserPageBinding::inflate)  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAdapterForFragment()
        setStoryHightlightRecyclerView()

    }

    override fun onResume() {
        super.onResume()

        binding.tbBack.setOnClickListener {
            finish()
        }

    }

    private fun setAdapterForFragment() {
        val adapter = TabFragmentAdapter(this.supportFragmentManager, lifecycle)

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
    private fun setStoryHightlightRecyclerView() {
        binding.rvStoryHightlight.setHasFixedSize(true)
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvStoryHightlight.layoutManager = linearLayoutManager
        val adapter = StoryHighlightAdapter(this, TempPageLists.storyHighlightAdpSlides)
        adapter.notifyDataSetChanged()
        binding.rvStoryHightlight.adapter = adapter
    }
}