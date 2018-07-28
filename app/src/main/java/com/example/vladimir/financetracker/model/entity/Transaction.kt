package com.example.vladimir.financetracker.model.entity

data class Transaction(val id: Int,
                       val name: String,
                       val type: String,
                       val category: String,
                       val value: Double)