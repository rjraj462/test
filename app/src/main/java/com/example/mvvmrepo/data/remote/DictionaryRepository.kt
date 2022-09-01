package com.example.mvvmrepo.data.remote

import com.example.mvvmrepo.data.base.DictionaryAPIService
import com.example.mvvmrepo.data.model.DictionaryDataResponse
import retrofit2.Call


class DictionaryRepository(private val apiService: DictionaryAPIService) {
    fun getDictionaryData(sf : String): Call<DictionaryDataResponse> {
        return apiService.requestDictionaryData(sf)
    }
}