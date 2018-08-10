package com.example.vladimir.financetracker.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.vladimir.financetracker.model.entity.Transaction

@Dao
interface TransactionDao : BaseDao<Transaction> {

    @Query("SELECT * FROM tx")
    fun getAll(): List<Transaction>

    @Query("SELECT * FROM tx WHERE id = :id")
    fun getById(id: Long): Transaction
}