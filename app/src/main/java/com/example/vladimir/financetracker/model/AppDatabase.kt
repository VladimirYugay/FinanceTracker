package com.example.vladimir.financetracker.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.vladimir.financetracker.model.dao.TransactionDao
import com.example.vladimir.financetracker.model.dao.WalletDao
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet
import android.arch.persistence.room.Room
import com.example.vladimir.financetracker.FinanceTrackerApplication

@Database(entities = arrayOf(
        Wallet::class,
        Transaction::class
), version = 1, exportSchema = false)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun walletDao(): WalletDao
    abstract fun transactionDao(): TransactionDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(FinanceTrackerApplication.INSTANCE, AppDatabase::class.java, "ftdb")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE!!
        }
    }
}