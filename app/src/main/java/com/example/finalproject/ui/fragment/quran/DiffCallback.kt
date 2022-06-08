package com.example.finalproject.ui.fragment.quran

import androidx.recyclerview.widget.DiffUtil
import com.example.finalproject.data.response.quranres.SurahsItem

class DiffCallback(val oldList: List<SurahsItem>, val newList: List<SurahsItem>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldData = oldList[oldItemPosition]
        val newData = newList[newItemPosition]
        return oldData.number == newData.number &&
                oldData.englishName == newData.englishName &&
                oldData.englishNameTranslation == newData.englishNameTranslation &&
                oldData.name == newData.name &&
                oldData.revelationType == newData.revelationType

    }

}