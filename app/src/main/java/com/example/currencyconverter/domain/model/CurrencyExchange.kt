package com.example.currencyconverter.domain.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "exchanges")
data class CurrencyExchange(
    @PrimaryKey @NonNull
    @SerializedName("first_currency_name") val first_currency_name: String,
    @SerializedName("first_currency_amount") val first_currency_amount: Double,
    @SerializedName("second_currency_name") val second_currency_name: String,
    @SerializedName("second_currency_amount") val second_currency_amount: Double,
    @SerializedName("date") val date: String,
    ) : Serializable
