package com.example.currencyconverter.domain.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "currencies")
data class CurrencyList (
    @PrimaryKey @NonNull
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: Double,
    val is_favorite: Boolean
) : Serializable