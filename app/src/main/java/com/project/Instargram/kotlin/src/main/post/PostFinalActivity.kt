package com.project.Instargram.kotlin.src.main.post

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.config.BaseActivity
import com.project.Instargram.kotlin.databinding.ActivityPostFinalBinding
import com.project.Instargram.kotlin.src.login.model.newaccount.NewAccountService
import com.project.Instargram.kotlin.src.main.MainActivity
import com.project.Instargram.kotlin.src.main.post.model.NewPostInterface
import com.project.Instargram.kotlin.src.main.post.model.NewPostResponse
import com.project.Instargram.kotlin.src.main.post.model.PostService
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.http.Multipart
import java.io.File
import java.io.IOException


class PostFinalActivity: BaseActivity<ActivityPostFinalBinding>(ActivityPostFinalBinding::inflate), NewPostInterface{

    private val KEY_USERID = "userIdx"
    private val KEY_SINGLE = "sigle_image"
    private val KEY_MULTI = "multi_image"
    private val ISITSINGLE = "is_it_single_image"

    private var userIdx:Int = 0
    private var postText:String = ""
    private var location:String = "null"
    private var photoTagUserList: List<String> = listOf("null")
    private var hashTag:String = "#null"
    private var postType:Int = 0
    var path: String  = ""

    private var finalImageList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isItSingle = intent.getBooleanExtra(ISITSINGLE, true)
        if(isItSingle == true) {
            val imageToPost = intent.getStringExtra(KEY_SINGLE).toString()
            finalImageList.add(imageToPost)

        } else {
            val imagesToPost = intent.getStringArrayListExtra(KEY_MULTI)!!
            imagesToPost.forEach{
                finalImageList.add(it)
            }
        }
        path = "/storage/emulated/0/Pictures/Naver/다운로드파일_20230310_214721.jpg"
        Log.d(TAG, "onCreate: post-> " + path)
    }


    override fun onResume() {
        super.onResume()

        userIdx= getIntegerValue(KEY_USERID)!!
        postType= 0

        Glide.with(this).load(finalImageList[0]).into(binding.imgThumbnail)

        binding.tbBack.setOnClickListener {
            finish()
        }
        binding.tbSave.setOnClickListener {
            postText= binding.edittext.text.toString()
            newPost()
        }
    }

    override fun onPostNewPostSuccess(response: NewPostResponse) {
        Log.d(TAG, "onPostNewAccountSuccess:  post->" + response)
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onPostNewPostFailure(message: String) {

    }

    fun newPost() {
        //image

        val requestImage: MutableList<MultipartBody.Part> = mutableListOf()
        finalImageList.forEach{
            val file: File = File(it)
            val imageFile : RequestBody = RequestBody.create( "image/jpg".toMediaTypeOrNull(), file)
            val requestElement = MultipartBody.Part.createFormData("post-files", "file", imageFile)
            requestImage.add(requestElement)
        }

        //data
        var jsonData: JSONObject? = getDataForNewPost()
        val data : RequestBody = RequestBody.create( "application/json".toMediaTypeOrNull(), jsonData.toString())
        val requestData = MultipartBody.Part.createFormData("post-data","data", data)
        Log.d(TAG, "RegesterAccount: json -> " + getDataForNewPost().toString())
        Log.d(TAG, "RegesterAccount: data -> " + data)

        PostService(this).tryPostNewPost(requestData, requestImage.toList())

    }


    fun getDataForNewPost(): JSONObject {
        val rootObject= JSONObject()
        rootObject.put("userIdx",userIdx)
        rootObject.put("postText",postText)
        rootObject.put("location",location)
        rootObject.put("photoTagUserList", JSONArray(photoTagUserList))
        rootObject.put("hashTag",hashTag)
        rootObject.put("postType",postType)


        Log.d(TAG, "getDataForNewPost: post-> " + rootObject)

        return rootObject
    }


}
