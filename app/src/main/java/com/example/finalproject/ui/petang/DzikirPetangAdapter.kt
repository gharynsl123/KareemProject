package com.example.finalproject.ui.petang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.response.DzikirPetangResponseItem
import com.example.finalproject.databinding.ItemListDzikirBinding
import com.example.finalproject.utils.OnItemPetangClickCallback

class DzikirPetangAdapter : RecyclerView.Adapter<DzikirPetangAdapter.MyViewHolder>() {

    private var listPetang = ArrayList<DzikirPetangResponseItem>()

    fun setData(dataPetang: List<DzikirPetangResponseItem>?) {
        if (dataPetang == null) return
        listPetang.clear()
        listPetang.addAll(dataPetang)
    }

    private var onItemClickCallBack: OnItemPetangClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemPetangClickCallback) {
        this.onItemClickCallBack = onItemClickCallback
    }

    class MyViewHolder(val binding: ItemListDzikirBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemListDzikirBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listPetang[position]
        holder.binding.apply {
            tvArabic.text = data.arab
            tvMeaning.text = data.terjemahan

            holder.itemView.setOnClickListener {
                onItemClickCallBack?.onItemClicked(data)
            }
        }
    }

    override fun getItemCount() = listPetang.size
}