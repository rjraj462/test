package com.example.mvvmrepo.data.base

import com.example.mvvmrepo.data.model.DictionaryDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DictionaryAPIService {
    @GET("software/acromine/dictionary.py")
    fun requestDictionaryData(@Query("sf") sf: String): Call<DictionaryDataResponse>
}
