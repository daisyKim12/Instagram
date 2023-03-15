package com.project.Instargram.kotlin.config

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.project.Instargram.kotlin.util.LoadingDialog
import java.text.SimpleDateFormat
import java.util.*

// Fragment의 기본을 작성, 뷰 바인딩 활용
abstract class BaseFragment<B : ViewBinding>(
    private val bind: (View) -> B,
    @LayoutRes layoutResId: Int
) : Fragment(layoutResId) {
    private var _binding: B? = null
    lateinit var mLoadingDialog: LoadingDialog

    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bind(super.onCreateView(inflater, container, savedInstanceState)!!)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun showCustomToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun showLoadingDialog(context: Context) {
        mLoadingDialog = LoadingDialog(context)
        mLoadingDialog.show()
    }

    fun dismissLoadingDialog() {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
    }

    fun getStringValue(KEY: String): String? {
        return ApplicationClass.sSharedPreferences.getString(KEY, "error")
    }

    fun getBooleanValue(KEY: String): Boolean? {
        return ApplicationClass.sSharedPreferences.getBoolean(KEY, true)
    }

    fun getIntegerValue(KEY: String): Int? {
        return ApplicationClass.sSharedPreferences.getInt(KEY, 0)
    }

    fun clearAllSharedPref() {
        ApplicationClass.sSharedPreferences.edit().clear().commit()
    }

    fun clearSharedPrefByKey(KEY: String) {
        ApplicationClass.sSharedPreferences.edit().remove(KEY).commit()
    }

    fun saveString(key: String, text: String) {
        val editor = ApplicationClass.sSharedPreferences.edit()
        editor.putString(key, text)
        editor.apply()
    }

    fun saveInteger(key: String, save: Int) {
        val editor = ApplicationClass.sSharedPreferences.edit()
        editor.putInt(key, save)
        editor.apply()
    }

    fun timeStamp2Since(timeStamp: String): String {
        val now = System.currentTimeMillis()
        val post: Long = java.lang.Long.valueOf(timeStamp) * 1000 // its need to be in milisecond
        val datePost: Date = Date(post)

        val since = now-post
        val dateSince: Date = Date(since)
        if(since > 8.64e+7){
            //하루
            return SimpleDateFormat("M월 d일").format(datePost)
        } else if (since > 3.6e+6) {
            //12시간
            return SimpleDateFormat("h시간 전").format(dateSince)
        } else if(since > 0){
            //1시간
            return SimpleDateFormat("m분 전").format(dateSince)
        } else {
            return ""
        }
    }

}