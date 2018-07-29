package com.example.vladimir.financetracker.model.entity

data class Wallet(val id: String,
                  var name: String,
                  var balance: Double = 0.0,
                  var selected: Boolean = false)