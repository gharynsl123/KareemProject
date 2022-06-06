package com.example.finalproject.ui.baca

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.response.quranres.AyahsItem
import com.example.finalproject.databinding.ItemBacaQuranBinding
import com.example.finalproject.utils.OnItemBacaQuranClickCallback

class BacaanQuranAdapter : RecyclerView.Adapter<BacaanQuranAdapter.MyViewHolder>() {

    private var listBacaan = ArrayList<AyahsItem>()

    fun setData(dataQuran: List<AyahsItem>?) {
        if (dataQuran == null) return
        listBacaan.clear()
        listBacaan.addAll(dataQuran)
    }

    private var onItemClickedCallBack: OnItemBacaQuranClickCallback? = null

    fun setOnItemClickBacaanCallback(onItemClickBacaanCallback: OnItemBacaQuranClickCallback) {
        this.onItemClickedCallBack = onItemClickBacaanCallback
    }

    class MyViewHolder(val binding: ItemBacaQuranBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemBacaQuranBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listBacaan[position]
        holder.binding.apply {
            ayatSurah.text = data.numberInSurah.toString()
            tvArabic.text = data.text

            holder.itemView.setOnClickListener {
                onItemClickedCallBack?.onItemClicked(data)
            }
        }
    }

    override fun getItemCount() = listBacaan.size
}