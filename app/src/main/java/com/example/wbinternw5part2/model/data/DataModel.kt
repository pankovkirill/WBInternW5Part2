package com.example.wbinternw5part2.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataModel(
    @SerializedName("results")
    val results: List<Results>,
) : Parcelable {

    @Parcelize
    data class Results(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("powerstats")
        val powerStats: PowerStats,
        @SerializedName("biography")
        val biography: Biography,
        @SerializedName("image")
        val image: ImageUrl
    ) : Parcelable {

        @Parcelize
        data class PowerStats(
            @SerializedName("intelligence")
            val intelligence: String,
            @SerializedName("speed")
            val speed: String,
            @SerializedName("power")
            val power: String,
        ) : Parcelable

        @Parcelize
        data class Biography(
            @SerializedName("full-name")
            val fullName: String
        ) : Parcelable

        @Parcelize
        data class ImageUrl(
            @SerializedName("url")
            val url: String
        ) : Parcelable
    }
}

