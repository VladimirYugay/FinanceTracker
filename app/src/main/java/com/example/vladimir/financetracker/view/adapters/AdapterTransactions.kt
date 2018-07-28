package com.example.vladimir.financetracker.view.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vladimir.financetracker.createId
import com.example.vladimir.financetracker.databinding.ItemTransactionsListBinding
import com.example.vladimir.financetracker.model.entity.Transaction

class AdapterTransactions : RecyclerView.Adapter<AdapterTransactions.ViewHolder>() {

    val mList = mutableListOf<Transaction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTransactions.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemTransactionsListBinding = ItemTransactionsListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemTransactionsListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList.get(position))
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    fun setTransactionsList(items: List<Transaction>) {
        items.forEach {
            mList.add(it)
            notifyItemInserted(mList.size - 1)
        }
    }

    class ViewHolder(val binding: ItemTransactionsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.transaction = transaction
            binding.executePendingBindings()
        }
    }
}