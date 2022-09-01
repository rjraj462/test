package com.example.mvvmrepo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmrepo.data.base.BaseService
import com.example.mvvmrepo.data.model.DictionaryDataResponse
import com.example.mvvmrepo.data.remote.DictionaryRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DictionaryViewModel : ViewModel() {
    val dictionaryResponseLiveData = MutableLiveData<DictionaryDataResponse>()
    var progressLiveData = MutableLiveData<Boolean>()
    var showAlertLiveData = MutableLiveData<String>()

    private val repository = DictionaryRepository(
        BaseService.getBaseApi()!!
    )

    fun getDictionaryData(sf: String) {
        progressLiveData.postValue(true)
        repository.getDictionaryData(sf).enqueue(object : Callback<DictionaryDataResponse> {

            override fun onFailure(call: Call<DictionaryDataResponse>, t: Throwable) {
                progressLiveData.postValue(false)
                showAlertLiveData.postValue(t.localizedMessage.toString())
            }

            override fun onResponse(
                call: Call<DictionaryDataResponse>,
                response: Response<DictionaryDataResponse>
            ) {
                progressLiveData.postValue(false)
                dictionaryResponseLiveData.postValue(
                    response.body()
                )
            }
        })
    }
}
