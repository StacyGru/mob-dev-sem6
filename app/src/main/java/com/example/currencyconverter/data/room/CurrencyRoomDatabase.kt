package com.example.currencyconverter.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currencyconverter.data.room.dao.CurrencyDao
import com.example.currencyconverter.domain.model.CurrencyList

@Database(entities = [CurrencyList::class], version = 2)
abstract class CurrencyRoomDatabase: RoomDatabase() {

    abstract fun getCurrencyDao(): CurrencyDao

    companion object {
        private var database: CurrencyRoomDatabase ?= null

        fun getInstance(context: Context): CurrencyRoomDatabase {
            return if (database == null) {
                database = Room
                    .databaseBuilder(context, CurrencyRoomDatabase::class.java, "database")
                    .fallbackToDestructiveMigration()
                    .build()
                database as CurrencyRoomDatabase
            } else {
                database as CurrencyRoomDatabase
            }        }
    }

}