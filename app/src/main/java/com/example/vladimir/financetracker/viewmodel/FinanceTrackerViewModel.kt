package com.example.vladimir.financetracker.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.vladimir.financetracker.model.AppDatabase
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet
import kotlinx.coroutines.experimental.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class FinanceTrackerViewModel : ViewModel() {

    val db: AppDatabase = AppDatabase.getDatabase()

    private var wallets: MutableList<Wallet> = mutableListOf()
    private var transactions: MutableList<Transaction> = mutableListOf()

    val observableWallets: MutableLiveData<MutableList<Wallet>> = MutableLiveData()
    val observableTransactions: MutableLiveData<MutableList<Transaction>> = MutableLiveData()
    val observableWallet: MutableLiveData<Wallet?> = MutableLiveData()

    private var editedTransaction: Transaction? = null

    private var USD = 60.0

    init {
        launch {
            loadExchangeRate()
        }

        setInitWallets(db.walletDao().getAll())
        setInitTransactions(db.transactionDao().getAll())
    }

    fun loadExchangeRate(){
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

    fun getUSD(): Double {
        return USD
    }

    fun setInitWallets(wallets: List<Wallet>) {
        this.wallets = wallets as MutableList<Wallet>

        observableWallets.value = this.wallets

        val defaultWallet = this.wallets.firstOrNull { it.isDefault }
        observableWallet.value = defaultWallet ?: this.wallets.firstOrNull()
    }

    fun setInitTransactions(transactions: List<Transaction>) {
        this.transactions = transactions as MutableList<Transaction>

        val filtered = this.transactions.filter { it.wallet == observableWallet.value?.name!! }
        observableTransactions.value = filtered.toMutableList()
    }

    fun addWallet(wallet: Wallet): Boolean {
        wallets.forEach {
            if (it.name == wallet.name) return false
        }

        db.walletDao().insert(wallet)
        observableWallets.value?.add(wallet)

        setWallet(wallet)

        return true
    }

    fun setWallet(wallet: Wallet) {
        wallets.forEach {
            if (it.isDefault && it.name != wallet.name) {
                db.walletDao().update(it.copy(isDefault = false))
                it.isDefault = false
            }
            if (it.name == wallet.name) {
                db.walletDao().update(it.copy(isDefault = true))
                it.isDefault = true
            }
        }

        observableWallets.value = wallets
        observableWallet.value = wallet

        val filteredTxs = transactions.filter { it.wallet == wallet.name }
        observableTransactions.value = filteredTxs.toMutableList()
    }

    fun changeWallet(walletName: String) {
        wallets.forEach {
            if (it.name == walletName) setWallet(it)
        }
    }

    fun addTransaction(transaction: Transaction) {
        var newTransaction : Transaction? = null
        if (transaction.currency == observableWallet.value?.currency) {
            newTransaction = transaction.copy(wallet = observableWallet.value!!.name)
        }
        else {
            if (transaction.currency == "USD" && observableWallet.value?.currency == "RUB") {
                newTransaction = transaction.copy(currency = "RUB", value = transaction.value * USD, wallet = observableWallet.value!!.name)
            }
            else {
                newTransaction = transaction.copy(currency = "USD", value = transaction.value / USD, wallet = observableWallet.value!!.name)
            }
        }

        db.transactionDao().insert(newTransaction)
        transactions.add(newTransaction)

        wallets.forEach {
            if (it.name == observableWallet.value!!.name) {
                it.balance += newTransaction.value
                db.walletDao().update(it)
                setWallet(it)
            }
        }
    }

    fun getEditedTransaction() = editedTransaction

    fun setEditedTransaction(transaction: Transaction) {
        this.editedTransaction = transaction
    }

    fun getTransactionDataById(transactionId: Long) = db.transactionDao().getById(transactionId)

    fun updateTransaction(transaction: Transaction) {
        editedTransaction?.name = transaction.name
        editedTransaction?.value = transaction.value
        editedTransaction?.category = transaction.category
        editedTransaction?.currency = transaction.currency
        editedTransaction?.date = transaction.date
        editedTransaction?.expenditure = transaction.expenditure

        db.transactionDao().update(editedTransaction!!)
        observableTransactions.value?.set(observableTransactions.value!!.indexOfFirst { it.id == editedTransaction!!.id }, editedTransaction!!)
    }

    fun deleteEditedTransaction() {
        db.transactionDao().delete(editedTransaction!!)
        val toDelete= observableTransactions.value?.first { it.id == editedTransaction!!.id }
//        observableWallet.value!!.balance -= toDelete?.value!!
        observableWallets.value!!.first { it.name == toDelete!!.wallet }.balance -= toDelete!!.value

        wallets.first { it.name == observableWallet.value!!.name }.apply {
            this.balance -= editedTransaction!!.value
            db.walletDao().update(this)
            setWallet(this)
        }

        observableTransactions.value?.remove(toDelete)
        editedTransaction = null
    }
}
