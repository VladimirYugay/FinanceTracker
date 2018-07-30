package com.example.vladimir.financetracker.model.repository

import android.arch.lifecycle.extensions.R.id.info
import android.provider.Settings.Global.putString
import android.util.Log
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOError
import java.io.IOException
import java.net.URL

class Repository {

    var USD = 60.0
    val wallets: MutableList<Wallet> = mutableListOf()
    val transactions: MutableList<Transaction> = mutableListOf()

    private object Holder {
        val INSTANCE = Repository()
    }

    companion object {
        val instance: Repository by lazy { Holder.INSTANCE }
    }

    init {
        //TEST DEFAULT WALLET
        val wallet1 = Wallet("Кошелек", 250.0)
        wallets.addAll(mutableListOf(wallet1))
        launch {
            getExchangeRate()
        }
    }

    fun initData() {
        //SIMULATE DATA FROM DB

//        val wallet2 = Wallet(createId(), "WALLET2", 550.0)
//        val wallet3 = Wallet(createId(), "WALLET3", 750.0)
//        val wallet4 = Wallet(createId(), "WALLET4", 150.0)


//        val transaction = Transaction(createId(), "Burger", true, "RUB", "FOOD", 100.0, "1984", wallet1.id)
//        val transaction2 = Transaction(createId(), "Sandwich", true, "USD", "FOOD", 80.0, "1984", wallet1.id)
//        val transaction3 = Transaction(createId(), "Latte", true, "RUB", "FOOD", 150.0, "1984", wallet2.id)
//        val transaction4 = Transaction(createId(), "Espresso", true, "RUB", "FOOD", 150.0, "1984", wallet3.id)
//        val transaction5 = Transaction(createId(), "Tea", true, "RUB", "FOOD", 150.0, "1984", wallet4.id)
//        val transaction6 = Transaction(createId(), "Naruto", true, "RUB", "FOOD", 100.0, "1984", wallet4.id)
//        transactions.addAll(mutableListOf(transaction, transaction2, transaction3, transaction4, transaction5, transaction6))
    }

    fun getExchangeRate(){
        val client = OkHttpClient()
        val url = "http://free.currencyconverterapi.com/api/v6/convert?q=USD_RUB&compact=ultra"
        val request = Request.Builder().url(url).build()
        try {
            val response = client.newCall(request).execute()
            response.body()?.string()?.let {
                val data = JSONObject(it)
                USD = data.getDouble("USD_RUB")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}