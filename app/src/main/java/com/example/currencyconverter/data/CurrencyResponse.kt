package com.example.currencyconverter.data

// здесь описываем то, в каком формате ПОЛУЧАЕМ данные через retrofit (от веб-сервиса)
data class CurrencyResponse(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)