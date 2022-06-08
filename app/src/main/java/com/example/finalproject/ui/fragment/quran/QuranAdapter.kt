package com.example.finalproject.ui.fragment.quran

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.response.quranres.SurahsItem
import com.example.finalproject.databinding.ItemListQuranBinding
import com.example.finalproject.utils.quranutil.OnItemQuranClickCallback

class QuranAdapter : RecyclerView.Adapter<QuranAdapter.MyViewHolder>(), Filterable {
    private var listQuran = ArrayList<SurahsItem>()

    private var quranList : ArrayList<SurahsItem> = ArrayList()
    private var quranListFiltered : ArrayList<SurahsItem> = ArrayList()

    fun setData(dataQuran: List<SurahsItem>?) {
//        if (dataQuran == null) return
//        listQuran.clear()
//        listQuran.addAll(dataQuran)
//        listQuran = arrayListOf<SurahsItem>()
        quranList = dataQuran as ArrayList<SurahsItem>
        quranListFiltered = quranList
        notifyDataSetChanged()
    }

    private var onItemClickCallback: OnItemQuranClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemQuranClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class MyViewHolder(val binding: ItemListQuranBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(

        ItemListQuranBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

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

    override fun getFilter(): android.widget.Filter {
        return object : android.widget.Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) quranListFiltered = quranList else {
                    val filteredList = ArrayList<SurahsItem>()
                    quranList
                        .filter {
                            (it.number?.contains(constraint!!))
                                    (it.englishName?.contains(constraint!!) == true)

                        }
                        .forEach { filteredList.add(it) }
                    quranListFiltered = filteredList

                }
                return FilterResults().apply { values = quranListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                quranListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<SurahsItem>
                notifyDataSetChanged()
            }
        }
    }
}