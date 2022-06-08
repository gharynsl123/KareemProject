package com.example.finalproject.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.data.network.quran.QuranApiClient
import com.example.finalproject.data.response.quranres.SurahsItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AmmaViewModel : ViewModel() {
    val quranResponse = MutableLiveData<List<SurahsItem>?>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()


    fun getQuran(
        responHandle: (List<SurahsItem>?) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        QuranApiClient.getApiService().getQuran().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandle(it.data?.surahs as List<SurahsItem>?)
            }, {
                errorHandler(it)
            })
    }

    fun getData() {
        isLoading.value = true
        getQuran({
            isLoading.value = false
            quranResponse.value = it
        }, {
            isLoading.value = true
            isError.value = it
        })
    }

}
