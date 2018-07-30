package com.example.vladimir.financetracker.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.vladimir.financetracker.databinding.ItemTransactionsListBinding
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet

class AdapterTransactions : RecyclerView.Adapter<AdapterTransactions.ViewHolder>() {

    val mTransactionsList = mutableListOf<Transaction>()
    lateinit var mWallet: Wallet

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTransactions.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemTransactionsListBinding = ItemTransactionsListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemTransactionsListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mTransactionsList[position])
    }

    override fun getItemCount(): Int {
        return mTransactionsList.size
    }


    fun setTransactionsList(items: MutableList<Transaction>?) {
        items?.forEach {
            mTransactionsList.add(it)
            notifyItemInserted(mTransactionsList.size - 1)
        }
    }

    class ViewHolder(val binding: ItemTransactionsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.transaction = transaction
            binding.executePendingBindings()
        }
    }
}