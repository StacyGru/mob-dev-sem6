package com.example.currencyconverter.domain.model

import java.io.Serializable

data class CurrencyList (
    val name: String,
    val value: Double
) : Serializable