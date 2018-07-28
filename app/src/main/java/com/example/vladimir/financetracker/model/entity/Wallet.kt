package com.example.vladimir.financetracker.model.entity

data class Wallet(val id: Int,
                  val name: String,
                  val balance: Double,
                  val transactions: List<Transaction>?)