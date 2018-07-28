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

    var mList: List<Transaction>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTransactions.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemTransactionsListBinding = ItemTransactionsListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemTransactionsListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList?.get(position)
                ?: Transaction(createId(), "Empty", "Emtpy", "USD", "BUY", 108.0))
    }

    override fun getItemCount(): Int {
        return if (mList == null) {
            0
        } else {
            mList!!.size
        }
    }


    fun setTransactionsList(items: List<Transaction>) {
        if (mList == null) {
            mList = items
            notifyItemRangeInserted(0, items.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return mList!!.size
                }

                override fun getNewListSize(): Int {
                    return items.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return mList!![oldItemPosition].id == mList!![newItemPosition].id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return mList!![oldItemPosition].id == mList!![newItemPosition].id
                }
            })
            mList = items
            result.dispatchUpdatesTo(this)
        }
    }

    class ViewHolder(val binding: ItemTransactionsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.transaction = transaction
            binding.executePendingBindings()
        }
    }
}