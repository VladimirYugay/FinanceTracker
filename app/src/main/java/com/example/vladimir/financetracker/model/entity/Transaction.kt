package com.example.vladimir.financetracker.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.vladimir.financetracker.fmtMoney

@Entity(tableName = "tx")
data class Transaction(
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "expenditure") var expenditure: Boolean,
        @ColumnInfo(name = "currency") var currency: String,
        @ColumnInfo(name = "category") var category: String,
        @ColumnInfo(name = "value") var value: Double,
        @ColumnInfo(name = "date") var date: String,
        @ColumnInfo(name = "wallet") var wallet: String
) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0

    fun formattedValue() = value.fmtMoney()
}