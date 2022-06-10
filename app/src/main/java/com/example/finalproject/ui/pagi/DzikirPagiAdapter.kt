package com.example.finalproject.ui.pagi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.response.DzikirPagiResponseItem
import com.example.finalproject.databinding.ItemListDzikirBinding

class DzikirPagiAdapter(private val click: (DzikirPagiResponseItem) -> Unit) :
    RecyclerView.Adapter<DzikirPagiAdapter.MyViewHolder>() {

    private var listPagi = ArrayList<DzikirPagiResponseItem>()
    fun setData(dataPagi: List<DzikirPagiResponseItem>?) {
        if (dataPagi == null) return
        listPagi.clear()
        listPagi.addAll(dataPagi)
    }

    class MyViewHolder(val binding: ItemListDzikirBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemListDzikirBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listPagi[position]
        holder.binding.apply {
            tvArabic.text = data.arab
            tvMeaning.text = data.terjemahan
            holder.itemView.setOnClickListener {
                click(data)
            }
        }
    }

    override fun getItemCount() = listPagi.size
}