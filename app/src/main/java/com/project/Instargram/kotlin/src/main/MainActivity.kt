package com.project.Instargram.kotlin.src.main

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColor
import com.project.Instargram.kotlin.R
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityMainBinding
import com.project.Instargram.kotlin.src.login.EnterAutoLoginActivity
import com.project.Instargram.kotlin.src.main.home.HomeFragment
import com.project.Instargram.kotlin.src.main.myPage.MyPageFragment
import com.project.Instargram.kotlin.src.main.post.PostFragment
import com.project.Instargram.kotlin.src.main.post.PostImageActivity
import com.project.Instargram.kotlin.src.main.reels.ReelsFragment
import com.project.Instargram.kotlin.src.main.search.SearchFragment


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBtmNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main_home -> {
                    binding.mainBtmNav.setBackgroundColor(Color.WHITE)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                }
                R.id.menu_main_search -> {
                    binding.mainBtmNav.setBackgroundColor(Color.WHITE)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commitAllowingStateLoss()
                }
                R.id.menu_main_post -> {

                    startActivity(Intent(this, PostImageActivity::class.java))

//                    binding.mainBtmNav.setBackgroundColor(Color.WHITE)
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.main_frm, PostFragment())
//                        .commitAllowingStateLoss()
                }
                R.id.menu_main_reels -> {
                    binding.mainBtmNav.setBackgroundColor(Color.WHITE)
//                    binding.mainBtmNav.setBackgroundColor(Color.BLACK)
//                    val menu: Menu = binding.mainBtmNav.getMenu()
//                    menu.findItem(R.id.menu_main_home).setIcon(R.drawable.ic_nav_home_white)


                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ReelsFragment())
                        .commitAllowingStateLoss()
                }
                R.id.menu_main_mypage -> {
                    binding.mainBtmNav.setBackgroundColor(Color.WHITE)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MyPageFragment())
                        .commitAllowingStateLoss()
                }
            }
            true
        }
    }
}

//        binding.mainBtmNav.run {
//            setOnItemSelectedListener { item ->
//                when (item.itemId) {
//                    R.id.menu_main_home -> {
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_frm, HomeFragment())
//                            .commitAllowingStateLoss()
//                    }
//                    R.id.menu_main_search -> {
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_frm, SearchFragment())
//                            .commitAllowingStateLoss()
//                    }
//                    R.id.menu_main_post -> {
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_frm, PostFragment())
//                            .commitAllowingStateLoss()
//                    }
//                    R.id.menu_main_reels -> {
//                        binding.mainBtmNav.setBackgroundColor(resources.getColor(R.color.blackForText))
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_frm, ReelsFragment())
//                            .commitAllowingStateLoss()
//                    }
//                    R.id.menu_main_mypage -> {
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_frm, MyPageFragment())
//                            .commitAllowingStateLoss()
//                    }
//                }
//                true
//            }
//            selectedItemId = R.id.menu_main_home
//        }
