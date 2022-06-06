package com.example.finalproject.ui.fragment.quran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.response.quranres.SurahsItem
import com.example.finalproject.databinding.ItemListQuranBinding
import com.example.finalproject.utils.OnItemQuranClickCallback


class AmmaAdapter : RecyclerView.Adapter<AmmaAdapter.MyViewHolder>() {
    class MyViewHolder( val binding: ItemListQuranBinding) : RecyclerView.ViewHolder(binding.root)



    private var listQuran = ArrayList<SurahsItem>()
    fun setData(dataQuran: List<SurahsItem>?) {
        if (dataQuran == null) return
        listQuran.clear()
        listQuran.addAll(dataQuran)
    }

    private var onItemClickCallback: OnItemQuranClickCallback? = null

    fun setOnItemClickCallback(onItemQuranClickCallback: OnItemQuranClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(

        ItemListQuranBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: AmmaAdapter.MyViewHolder, position: Int) {

        val data = listQuran[position]
        holder.binding.apply {
            numberSurah.text = data.number.toString()
            englishName.text = data.englishName
            revelationType.text = data.revelationType
            englishMeaning.text = data.englishNameTranslation
            tvArabic.text = data.name

            holder.itemView.setOnClickListener {
                onItemClickCallback?.onItemClicked(data)
            }


        }
    }

    override fun getItemCount() = listQuran.size


}