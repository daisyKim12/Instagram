package com.project.Instargram.kotlin.src.main.search

import com.project.Instargram.kotlin.config.ApplicationClass
import com.project.Instargram.kotlin.src.main.MainRetrofitInterface
import com.project.Instargram.kotlin.src.main.page.model.post.GetUserPostResponse
import com.project.Instargram.kotlin.src.main.page.model.profile.GetProfileResponse
import com.project.Instargram.kotlin.src.main.search.model.GetWithoutSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchService(val searchInterface: SearchInterface) {

    fun tryGetWitoutSearch(userIdx: Int, page_num: Int){
        val mainRetrofitInterface = ApplicationClass.sRetrofit.create(MainRetrofitInterface::class.java)
        mainRetrofitInterface.getWithoutSearch(userIdx, page_num).enqueue(object : Callback<GetWithoutSearchResponse?> {
            override fun onResponse(
                call: Call<GetWithoutSearchResponse?>,
                response: Response<GetWithoutSearchResponse?>
            ) {
                searchInterface.onGetWithoutSearchSuccess(response.body() as GetWithoutSearchResponse)
            }

            override fun onFailure(call: Call<GetWithoutSearchResponse?>, t: Throwable) {
                searchInterface.onGetWithoutSearchFailure("통신 오류")
            }
        })
    }
}