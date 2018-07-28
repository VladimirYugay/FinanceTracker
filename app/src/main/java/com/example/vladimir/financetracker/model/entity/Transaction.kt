package com.example.vladimir.financetracker.model.entity

data class Transaction(val id: String,
                       val name: String,
                       val type: String,
                       val currency: String,
                       val category: String,
                       val value: Double,
                       val date: String)