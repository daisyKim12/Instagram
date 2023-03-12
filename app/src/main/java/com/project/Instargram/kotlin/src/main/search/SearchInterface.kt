package com.project.Instargram.kotlin.src.main.search

import com.project.Instargram.kotlin.src.main.search.model.GetWithoutSearchResponse

interface SearchInterface {

    fun onGetWithoutSearchSuccess(response: GetWithoutSearchResponse)

    fun onGetWithoutSearchFailure(message: String)
}