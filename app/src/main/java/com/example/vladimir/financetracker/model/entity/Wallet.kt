package com.example.vladimir.financetracker.model.entity

data class Wallet(
                  var name: String,
                  var balance: Double = 0.0,
                  var transactions: MutableList<Transaction> = mutableListOf())