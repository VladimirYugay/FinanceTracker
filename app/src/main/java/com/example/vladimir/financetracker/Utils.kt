package com.example.vladimir.financetracker

import android.app.Activity
import android.content.Intent
import java.text.DecimalFormat

fun formatNumber(number: Double): String {
    return DecimalFormat("#.00").format(number)
}