package com.project.Instargram.kotlin.config

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.project.Instargram.kotlin.util.ErrorDialog
import com.project.Instargram.kotlin.util.LoadingDialog


// 액티비티의 기본을 작성, 뷰 바인딩 활용
abstract class BaseActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {
    protected lateinit var binding: B
        private set
    lateinit var mLoadingDialog: LoadingDialog
    lateinit var mErrorDialog: ErrorDialog

    // 뷰 바인딩 객체를 받아서 inflate해서 화면을 만들어줌.
    // 즉 매번 onCreate에서 setContentView를 하지 않아도 됨.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }

    // 로딩 다이얼로그, 즉 로딩창을 띄워줌.
    // 네트워크가 시작될 때 사용자가 무작정 기다리게 하지 않기 위해 작성.
    fun showLoadingDialog(context: Context) {
        mLoadingDialog = LoadingDialog(context)
        mLoadingDialog.show()
    }
    // 띄워 놓은 로딩 다이얼로그를 없앰.
    fun dismissLoadingDialog() {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
    }

    fun showErroDialog(context: Context, title: String, message: String) {
        mErrorDialog = ErrorDialog(context, title, message)
        mErrorDialog.show()
    }

    // 토스트를 쉽게 띄울 수 있게 해줌.
    fun showCustomToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun saveString(key: String, text: String) {
        val editor = ApplicationClass.sSharedPreferences.edit()
        editor.putString(key, text)
        editor.apply()
    }

    fun saveBoolean(key: String, save: Boolean) {
        val editor = ApplicationClass.sSharedPreferences.edit()
        editor.putBoolean(key, save)
        editor.apply()
    }

    fun saveInteger(key: String, save: Int) {
        val editor = ApplicationClass.sSharedPreferences.edit()
        editor.putInt(key, save)
        editor.apply()
    }

    fun getStringValue(KEY: String): String? {
        return ApplicationClass.sSharedPreferences.getString(KEY, "error")
    }

    fun getBooleanValue(KEY: String): Boolean? {
        return ApplicationClass.sSharedPreferences.getBoolean(KEY, true)
    }

    fun clearAllSharedPref() {
        ApplicationClass.sSharedPreferences.edit().clear().commit()
    }

    fun clearSharedPrefByKey(KEY: String) {
        ApplicationClass.sSharedPreferences.edit().remove(KEY).commit()
    }


//    open fun path2uri(filePath: String): Uri? {
//        val cursor = this.contentResolver.query(
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
//            "_data = '$filePath'", null, null
//        )
//        cursor!!.moveToNext()
//        val id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"))
//        return ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toLong())
//    }
    fun uri2path(uri: Uri): String? {

        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = contentResolver.query(uri, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result
    }

    fun changePhoneFormat(phone: String): String? {
        if(phone.length == 11) {
            return phone.slice(0..2) + "-" + phone.slice(3..6) + "-" + phone.slice(7..10)
        } else {
            return null
        }
    }
}