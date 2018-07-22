package com.example.computations

import android.media.VolumeShaper
import com.example.computations.entity.Operation
import java.text.DecimalFormat
import kotlin.math.round

class MoneyOperations{
    companion object {

        //Основная валюта - доллар, все будем хранить там, при надобности - конвертим
        fun getBalance(): Double{
            //Достанем из базы или сети лист с объектами Operation
            val operations = arrayListOf<Operation>()
            operations.add(Operation("BUY", 150.0, "USD"))
            operations.add(Operation("BUY", 150.0, "USD"))
            operations.add(Operation("BUY", 150.0, "USD"))
            operations.add(Operation("BUY", 150.0, "USD"))
            var balance = 0.0
            for(operation in operations){
                balance += getOperationValue(operation)
            }
            return balance
        }

        //Передаем условный множитель, т.е. е
        fun convertCurrency(multiplier: Double): Double{
            return getBalance() / multiplier
        }

        private fun getOperationValue(operation: Operation): Double{
            return if(operation.type == "BUY"){
                - operation.value
            }else{
                operation.value
            }
        }
    }
}