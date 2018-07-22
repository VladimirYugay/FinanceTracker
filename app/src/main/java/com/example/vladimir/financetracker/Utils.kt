package com.example.vladimir.financetracker

import android.app.Activity
import android.content.Intent
import java.text.DecimalFormat

class Utils{
    companion object {
        fun formatNumber(number: Double) : String{
            return DecimalFormat("#.00").format(number)
        }
    }
}