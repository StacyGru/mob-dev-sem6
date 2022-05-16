package com.example.currencyconverter.domain.model

// здесь описываем то, в каком формате ПОЛУЧАЕМ данные через retrofit (от веб-сервиса)
data class DataResponse(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)