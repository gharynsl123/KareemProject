package com.example.finalproject.ui.petang

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.data.network.ApiClientPagi
import com.example.finalproject.data.response.DzikirPetangResponseItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class PetangViewModel : ViewModel() {
    val petangResponse = MutableLiveData<List<DzikirPetangResponseItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    fun getDzikirPetang(
        responseHandler: (List<DzikirPetangResponseItem>) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        ApiClientPagi.getApiService().getDzikirPetang().subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe({
            responseHandler(it)
        }, {
            errorHandler(it)
        })
    }

    fun getData() {
        isLoading.value = true
        getDzikirPetang({
            isLoading.value = false
            petangResponse.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }
}