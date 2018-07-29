package com.example.vladimir.financetracker.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.vladimir.financetracker.databinding.ItemTransactionsListBinding
import com.example.vladimir.financetracker.databinding.ItemWalletsListBinding
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet

class AdapterWallets : RecyclerView.Adapter<AdapterWallets.ViewHolder>() {

    val mList = mutableListOf<Wallet>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterWallets.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemWalletsListBinding = ItemWalletsListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemWalletsListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList.get(position))
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    fun setWalletsList(items: List<Wallet>) {
        items.forEach {
            mList.add(it)
            notifyItemInserted(mList.size - 1)
        }
    }

    class ViewHolder(val binding: ItemWalletsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(wallet: Wallet) {
            binding.wallet = wallet
            binding.executePendingBindings()
        }
    }
}