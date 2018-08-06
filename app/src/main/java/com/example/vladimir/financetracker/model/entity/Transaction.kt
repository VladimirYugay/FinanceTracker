package com.example.vladimir.financetracker.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tx")
data class Transaction(
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "expenditure") val expenditure: Boolean,
        @ColumnInfo(name = "currency") val currency: String,
        @ColumnInfo(name = "category") val category: String,
        @ColumnInfo(name = "value") val value: Double,
        @ColumnInfo(name = "date") val date: String,
        @ColumnInfo(name = "wallet") val wallet: String
) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0

    fun formattedValue() = String.format("%.2f", value).replace(",",".")
}