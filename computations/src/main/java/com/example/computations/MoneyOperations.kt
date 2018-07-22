package com.example.computations

import android.media.VolumeShaper
import java.text.DecimalFormat
import kotlin.math.round

class MoneyOperations{
    companion object {

        //Основная валюта - доллар
        fun getBalance(): Double{
            return 250.0
        }

        //Передаем условный множитель, т.е. е
        fun translateCurrency(multiplier: Double): Double{
            return getBalance() / multiplier
        }
    }
}