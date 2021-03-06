package com.example.finalproject.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DzikirPagiResponseItem(

    @field:SerializedName("faedah")
    val faedah: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("terjemahan")
    val terjemahan: String? = null,

    @field:SerializedName("arab")
    val arab: String? = null
) : Parcelable
