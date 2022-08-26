package com.example.finalproject.ui.pagi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.data.network.ApiClientPagi
import com.example.finalproject.data.response.DzikirPagiResponseItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class PagiViewModel : ViewModel() {
    val pagiResponse = MutableLiveData<List<DzikirPagiResponseItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    fun getDzikirPagi(
        responseHandler: (List<DzikirPagiResponseItem>) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        ApiClientPagi.getApiService().getDzikirPagi().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe({
            responseHandler(it)
        }, {
            errorHandler(it)
        })
    }

    fun getData() {
        isLoading.value = true
        getDzikirPagi({
            isLoading.value = false
            pagiResponse.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }
}