package com.example.vladimir.financetracker.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.databinding.ItemTransactionsListBinding
import com.example.vladimir.financetracker.getColor
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet

class AdapterTransactions (
        private val onItemClick: (Transaction) -> Unit
): RecyclerView.Adapter<AdapterTransactions.ViewHolder>() {

    val mTransactionsList = mutableListOf<Transaction>()
    lateinit var mWallet: Wallet

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTransactions.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemTransactionsListBinding = ItemTransactionsListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemTransactionsListBinding, onItemClick)
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

    class ViewHolder(
            val binding: ItemTransactionsListBinding,
            private val onItemCilck: (Transaction) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction) {
            binding.transaction = transaction
            binding.executePendingBindings()
            itemView.findViewById<TextView>(R.id.item_transactions_list_value).setTextColor(if (transaction.value < 0) getColor(R.color.color_negative) else getColor(R.color.color_positive))
            itemView.setOnClickListener {
                onItemCilck.invoke(transaction)
            }
        }
    }
}