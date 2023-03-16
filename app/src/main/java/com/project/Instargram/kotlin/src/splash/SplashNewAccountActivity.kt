package com.project.Instargram.kotlin.src.splash

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivitySplashNewAccountBinding
import com.project.Instargram.kotlin.databinding.DialogDatePickerBinding
import com.project.Instargram.kotlin.src.login.activity.EnterCertificationActivity
import com.project.Instargram.kotlin.src.login.activity.LoginActivity
import com.project.Instargram.kotlin.src.login.model.newaccount.NewAccountInterface
import com.project.Instargram.kotlin.src.login.model.newaccount.NewAccountResponse
import com.project.Instargram.kotlin.src.login.model.newaccount.NewAccountService
import com.project.Instargram.kotlin.src.main.MainActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File

class SplashNewAccountActivity: BaseActivity<ActivitySplashNewAccountBinding>(ActivitySplashNewAccountBinding::inflate), NewAccountInterface {

    private val KEY_IMAGE_URI = "image_path"
    private val KEY_NICKNAME = "nickName"
    private val KEY_ISIT_EMAIL = "isItEmail"
    private val X_ACCESS_TOKEN = "jwtToken"

    private lateinit var handler: Handler
    private var isItEmail = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isItEmail = getBooleanValue(KEY_ISIT_EMAIL)!!

        handler = Handler(Looper.getMainLooper())
        //val absolutePath: String = "/storage/emulated/0/Pictures/Instagram/IMG_20220726_165002_301.jpg"
        //binding.circleImageView.setImageURI(path2uri(absolutePath))
        regesterAccount()
    }

    fun waitAndMoveToNextActivity() {

        ///button progress
        Thread() {
            handler.post(Runnable {

            })
            Thread.sleep(5000)
            //Log.d(ContentValues.TAG, "waitAndMoveToNext: "+ )


            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }.start()

    }

    override fun onPostNewAccountSuccess(response: NewAccountResponse) {
        Log.d(ContentValues.TAG, "onPostNewAccountSuccess: " + response)
        val absolutePath = response.result.imgURL
        Log.d(TAG, "onPostNewAccountSuccess: "+ absolutePath)
        val nickName = getStringValue(KEY_NICKNAME)
        //change path to uri
//        binding.circleImageView.setImageURI(path2uri(absolutePath))
        binding.txtDetail1.text = nickName + "님, Instagram에 오신 것을 환영합니다"
        clearExtraSharedPref()
        Thread.sleep(3000)

        waitAndMoveToNextActivity()

    }

    override fun onPostNewAccountFailure(message: String) {
        Log.d(ContentValues.TAG, "onPostNewAccountSuccess: " + message)
    }

    fun regesterAccount() {
        //image
        val file: File = File(getStringValue(KEY_IMAGE_URI))
        Log.d(TAG, "RegesterAccount: path -> " + getStringValue(KEY_IMAGE_URI))
        Log.d(TAG, "RegesterAccount: file -> " + file)
        val imageFile : RequestBody = RequestBody.create( "image/jpg".toMediaTypeOrNull(), file)
        val requestImage = MultipartBody.Part.createFormData("file",file.name, imageFile)

        //data
        var jsonData: JSONObject? = null
        if(isItEmail == true) {
            jsonData = getDataForEmailAccount()
        } else {
            jsonData = getDataForPhoneAccount()
        }

        val data : RequestBody = RequestBody.create( "application/json".toMediaTypeOrNull(), jsonData.toString())
        val requestData = MultipartBody.Part.createFormData("sign-up-data","data", data)
        Log.d(TAG, "RegesterAccount: json -> " + getDataForEmailAccount().toString())
        Log.d(TAG, "RegesterAccount: data -> " + data)

        if(isItEmail == true) {
            NewAccountService(this).tryPostEmailNewAccount(requestData, requestImage)
        } else {
            NewAccountService(this).tryPostPhoneNewAccount(requestData, requestImage)
        }
    }

    fun getDataForEmailAccount(): JSONObject {
        val email: String? = getStringValue("email")
        val password: String? = getStringValue("password")
        val name: String? = getStringValue("name")
        val nickName: String? = getStringValue("nickName")
        val birthday: String? = getStringValue("birthday")

        val rootObject= JSONObject()
        rootObject.put("email",email)
        rootObject.put("password",password)
        rootObject.put("name",name)
        rootObject.put("nickName",nickName)
        rootObject.put("birthday",birthday)

        Log.d(TAG, "getDataForEmailAccount: json -> " + rootObject)

        return rootObject
    }

    fun getDataForPhoneAccount(): JSONObject {
        val phone: String? = getStringValue("phone")
        val password: String? = getStringValue("password")
        val name: String? = getStringValue("name")
        val nickName: String? = getStringValue("nickName")
        val birthday: String? = getStringValue("birthday")

        val rootObject= JSONObject()
        rootObject.put("phone",phone)
        rootObject.put("password",password)
        rootObject.put("name",name)
        rootObject.put("nickName",nickName)
        rootObject.put("birthday",birthday)

        Log.d(TAG, "getDataForEmailAccount: json -> " + rootObject)

        return rootObject
    }

    fun clearExtraSharedPref() {
        clearSharedPrefByKey("birthday")
        clearSharedPrefByKey("password")
        clearSharedPrefByKey("phone")
        clearSharedPrefByKey("name")
        clearSharedPrefByKey("email")
        clearSharedPrefByKey("isItEmail")
    }

}



























