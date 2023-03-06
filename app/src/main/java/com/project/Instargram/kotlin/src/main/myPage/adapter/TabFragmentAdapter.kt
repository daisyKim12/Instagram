package com.project.Instargram.kotlin.src.main.myPage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.project.Instargram.kotlin.src.main.myPage.fragment.MyPostFragment
import com.project.Instargram.kotlin.src.main.myPage.fragment.MyReelsFragment
import com.project.Instargram.kotlin.src.main.myPage.fragment.MyTagedFragment

class TabFragmentAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var tempFragment: Fragment = MyPostFragment()
        when(position) {
            0 -> tempFragment = MyPostFragment()
            1 -> tempFragment = MyReelsFragment()
            2 -> tempFragment = MyTagedFragment()
        }
        return tempFragment
    }
}