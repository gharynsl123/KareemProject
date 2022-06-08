package com.example.finalproject.data.response.quranres

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuranResponse(

    @field:SerializedName("data")
    val data: Data? = null,
) : Parcelable

@Parcelize
data class Data(

    @field:SerializedName("surahs")
    val surahs: List<SurahsItem>? = null
) : Parcelable, List<QuranResponse>

@Parcelize
data class SurahsItem(

    @field:SerializedName("number")
    val number: String? = null,

    @field:SerializedName("englishName")
    val englishName: String? = null,

    @field:SerializedName("revelationType")
    val revelationType: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("ayahs")
    val ayahs: List<AyahsItem?>? = null,

    @field:SerializedName("englishNameTranslation")
    val englishNameTranslation: String? = null
) : Parcelable

@Parcelize
data class AyahsItem(

    @field:SerializedName("text")
    val text: String? = null,

    @field:SerializedName("numberInSurah")
    val numberInSurah: Int? = null,

    @field:SerializedName("juz")
    val juz: Int? = null
) : Parcelable
