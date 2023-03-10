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
import com.project.Instargram.kotlin.src.login.activity.EnterCertificationActivity
import com.project.Instargram.kotlin.src.login.model.newaccount.NewAccountInterface
import com.project.Instargram.kotlin.src.login.model.newaccount.NewAccountResponse
import com.project.Instargram.kotlin.src.login.model.newaccount.NewAccountService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File

class SplashNewAccountActivity: BaseActivity<ActivitySplashNewAccountBinding>(ActivitySplashNewAccountBinding::inflate), NewAccountInterface {

    private val KEY_IMAGE_URI = "image_path"
    private val KEY_NICKNAME = "nickName"
    private lateinit var handler: Handler



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handler = Handler(Looper.getMainLooper())

        RegesterAccount()
    }

    fun waitAndMoveToNextActivity() {

        ///button progress
        Thread() {
            handler.post(Runnable {

            })
            Thread.sleep(5000)
            //Log.d(ContentValues.TAG, "waitAndMoveToNext: "+ )

            //show welcome screen
            Thread.sleep(3000)

            val intent = Intent(this, EnterCertificationActivity::class.java)
            startActivity(intent)
        }.start()

    }

    override fun onPostNewAccountSuccess(response: NewAccountResponse) {
        Log.d(ContentValues.TAG, "onPostNewAccountSuccess: " + response)
        val absolutePath = response.result.imgURL.toString()
        val nickName = getValue(KEY_NICKNAME)
        //change path to uri
        //binding.circleImageView.setImageURI()
        binding.txtDetail1.text = nickName + "님, Instagram에 오신 것을 환영합니다"
    }

    override fun onPostNewAccountFailure(message: String) {
        Log.d(ContentValues.TAG, "onPostNewAccountSuccess: " + message)

    }

    fun RegesterAccount() {
        //val string = "multipart/form-data"
        //application/json
        //image/jpg

        //data
        val data : RequestBody = RequestBody.create( "application/json".toMediaTypeOrNull(), getDataForEmailAccount().toString())
        Log.d(TAG, "RegesterAccount: data -> " + data)

        //image
        val file: File = File(getValue(KEY_IMAGE_URI))
        Log.d(TAG, "RegesterAccount: url -> " + getValue(KEY_IMAGE_URI))
        Log.d(TAG, "RegesterAccount: file -> " + file)

        val imageFile : RequestBody = RequestBody.create( "multipart/form-data".toMediaTypeOrNull(), file)
        val requestImage = MultipartBody.Part.createFormData("file",file.name, imageFile)
        NewAccountService(this).tryPostNewAccount(data, requestImage)


//        val gson = GsonBuilder().setLenient().create()
//        val modelBody: RequestBody = RequestBody.create("application/json".toMediaTypeOrNull(), gson.toJson(getDataForEmailAccount().toString()))
//        val reqFileBody: RequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
//        val mPart1: MultipartBody.Part = MultipartBody.Part.createFormData("file", file.name, reqFileBody)

//        LoginService(this).tryPostNewAccount(modelBody, mPart1)

    }

    fun getDataForEmailAccount(): JSONObject {
        val email: String? = getValue("email")
        val password: String? = getValue("password")
        val name: String? = getValue("name")
        val nickName: String? = getValue("nickName")
        val birthday: String? = getValue("birthday")

        val rootObject= JSONObject()
        rootObject.put("email",email)
        rootObject.put("password",password)
        rootObject.put("name",name)
        rootObject.put("nickName",nickName)
        rootObject.put("birthday",birthday)

        Log.d(TAG, "getDataForEmailAccount: json -> " + rootObject)

        return rootObject
    }

}