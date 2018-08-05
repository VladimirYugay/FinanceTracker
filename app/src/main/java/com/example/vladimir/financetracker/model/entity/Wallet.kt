package com.example.vladimir.financetracker.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "wallet")
data class Wallet(
        @PrimaryKey
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "balance") var balance: Double = 0.0,
        @ColumnInfo(name = "isDefault") var isDefault: Boolean = false
)
