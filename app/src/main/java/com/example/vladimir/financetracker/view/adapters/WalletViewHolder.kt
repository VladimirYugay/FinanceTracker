package com.example.vladimir.financetracker.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.fmtMoney
import com.example.vladimir.financetracker.getColor
import com.example.vladimir.financetracker.model.entity.Wallet

class WalletViewHolder (
        view: View,
        private val onItemClick: (Wallet) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val textName = view.findViewById<TextView>(R.id.item_wallet_name)
    private val textAmount = view.findViewById<TextView>(R.id.item_wallet_amount)
    private val textCurrency = view.findViewById<TextView>(R.id.item_wallet_currency)

    fun bind(wallet: Wallet) {
        textName.text = wallet.name
        textAmount.text = wallet.balance.fmtMoney()
        textCurrency.text = if (wallet.currency == "RUB") "\u20BD" else "$"
        textAmount.setTextColor(if (wallet.balance < 0) getColor(R.color.color_negative) else getColor(R.color.color_positive))
        itemView.setBackgroundColor(if (wallet.isDefault) getColor(R.color.colorAccentLight) else getColor(R.color.colorWhite))
        itemView.setOnClickListener {
            onItemClick.invoke(wallet)
        }
    }
}