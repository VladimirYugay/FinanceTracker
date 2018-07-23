package com.example.computations

import android.media.VolumeShaper
import com.example.computations.entity.Operation
import java.text.DecimalFormat
import kotlin.math.round

class MoneyOperations{
    companion object {

        //Основная валюта - доллар, все будем хранить в долларах, при надобности - конвертим
        fun getBalance(balance: Double, operations: ArrayList<Operation>): Double{
            var updatedBalance = balance
            for(operation in operations){
                updatedBalance -= operation.value
            }
            return updatedBalance
        }

        //Передаем условный множитель, т.е. е
        fun convertCurrency(currentBalance: Double, multiplier: Double): Double{
            return currentBalance / multiplier
        }

    }
}