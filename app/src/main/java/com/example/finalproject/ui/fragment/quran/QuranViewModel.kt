package com.example.finalproject.ui.fragment.quran

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.data.network.ApiClientQuran
import com.example.finalproject.data.response.quranres.SurahsItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers

class QuranViewModel : ViewModel() {


    val quranResponse = MutableLiveData<List<SurahsItem>?>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    val isSearchLoading = MutableLiveData(false)
    val isSearchError = MutableLiveData<Throwable?>()

    // Remote
    private fun <T : Any> loadData(
        apiCall: Flowable<T>,
        responseHandler: (T) -> Unit,
        errorHandler: (Throwable) -> Unit,
    ) {
        apiCall.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                responseHandler(response)
            }, { error ->
                errorHandler(error)
            })
    }

    fun searchQuranByQuery(query: String) {
        isSearchLoading.value = true
        loadData(
            ApiClientQuran.getApiService().searchQuranByQuery(query),
            {
                quranResponse.value = it.data?.surahs
                isSearchError.value = null
                isSearchLoading.value = false
            },
            {
                quranResponse.value = null
                isSearchError.value = it
                isSearchLoading.value = false
            }
        )
    }

    fun getQuran(
        responHandle: (List<SurahsItem>?) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        ApiClientQuran.getApiService().getQuran().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandle(it.data?.surahs)
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
            isLoading.value = false
            isError.value = it
        })
    }

}
