package com.project.Instargram.kotlin.src.main.post.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log

class ImagesGallery {

    companion object {
        fun listOfImages(context: Context): ArrayList<String> {

            var uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            var cursor: Cursor?
            var column_index_data:Int = 0
            //var column_index_folder_name:Int = 0
            var listOfAllImages: ArrayList<String> = arrayListOf()
            var absolutePathOfImage: String = ""

            val projection : Array<String> = arrayOf(MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

            val orderBy: String = MediaStore.Video.Media.DATE_TAKEN

            cursor = context.contentResolver.query(uri, projection, null, null, orderBy+" DESC")

            column_index_data = cursor!!.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)

            //get folder name
            //column_index_folder_name =  cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

            //Log.d(TAG, "listOfImages: " + cursor.count.toString())

            var cnt = 0
            while (cursor.moveToNext()) {

                if(cnt++ == 40) {
                    break
                }
                absolutePathOfImage = cursor.getString(column_index_data)
                listOfAllImages.add(absolutePathOfImage)


            }

            return listOfAllImages
        }
    }

}