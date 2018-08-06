package com.example.vladimir.financetracker.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.vladimir.financetracker.model.entity.Wallet

@Dao
interface WalletDao : BaseDao<Wallet> {

    @Query("SELECT * FROM wallet")
    fun getAll(): List<Wallet>

    @Query("SELECT * FROM wallet WHERE name = :name")
    fun getById(name: String): Wallet
}