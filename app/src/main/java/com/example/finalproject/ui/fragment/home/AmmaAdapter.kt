package com.example.finalproject.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.response.quranres.SurahsItem
import com.example.finalproject.databinding.ItemListQuranBinding
import kotlin.Int as Int1

class AmmaAdapter : RecyclerView.Adapter<AmmaAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemListQuranBinding) : RecyclerView.ViewHolder(binding.root)

    private var listQuran = ArrayList<SurahsItem>()
    fun setData(dataQuran: List<SurahsItem>?) {
        if (dataQuran == null) return
        listQuran.clear()
        listQuran.addAll(dataQuran)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int1) = MyViewHolder(

        ItemListQuranBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int1) {

        val data = listQuran[position]
        holder.binding.apply {
            numberSurah.text = data.number.toString()
            englishName.text = data.englishName
            revelationType.text = data.revelationType
            englishMeaning.text = data.englishNameTranslation
        }
    }

    override fun getItemCount() = listQuran.size

}