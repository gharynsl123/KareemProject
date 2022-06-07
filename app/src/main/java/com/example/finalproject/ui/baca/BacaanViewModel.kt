package com.example.finalproject.ui.baca

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.data.network.quran.QuranApiClient
import com.example.finalproject.data.response.quranres.AyahsItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class BacaanViewModel : ViewModel() {
    val bacaanResponse = MutableLiveData<List<AyahsItem>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    fun getBacaanQuran(
        responHandle: (List<AyahsItem>) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        QuranApiClient.getApiService().getQuran().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandle(it.data?.ayahs as List<AyahsItem>)
            }, {
                errorHandler(it)
            })
    }

    fun getData() {
        isLoading.value = true
        getBacaanQuran({
            isLoading.value = false
            bacaanResponse.value = it
        }, {
            isLoading.value = true
            isError.value = it
        })
    }
}