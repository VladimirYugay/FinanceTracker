package com.example.vladimir.financetracker.model.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.vladimir.financetracker.model.entity.Transaction

@Dao
interface TransactionDao : BaseDao<Transaction> {

    @Query("SELECT * FROM tx")
    fun getAll(): LiveData<List<Transaction>>
}