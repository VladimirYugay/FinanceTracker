package com.example.vladimir.financetracker.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.model.entity.Wallet

class WalletsRecyclerViewAdapter (
        private val onItemClick: (Wallet) -> Unit
) : RecyclerView.Adapter<WalletViewHolder>() {

    private val items: ArrayList<Wallet> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) =
            WalletViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_wallet, parent, false), onItemClick)

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(items: List<Wallet>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}
