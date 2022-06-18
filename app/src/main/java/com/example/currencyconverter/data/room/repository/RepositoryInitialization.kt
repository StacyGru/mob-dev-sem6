package com.example.currencyconverter.data.room.repository

import android.content.Context
import com.example.currencyconverter.data.room.CurrencyRoomDatabase
import com.example.currencyconverter.data.room.dao.CurrencyDao

object RepositoryInitialization {
    private lateinit var repositoryRealization: CurrencyRepositoryRealization
    private var currencyDao: CurrencyDao? = null

    fun getRepository(context: Context): CurrencyRepositoryRealization {
        if (currencyDao == null) {
            currencyDao = CurrencyRoomDatabase.getInstance(context).getCurrencyDao()
            repositoryRealization = CurrencyRepositoryRealization(currencyDao!!)
        }
        return repositoryRealization
    }
}