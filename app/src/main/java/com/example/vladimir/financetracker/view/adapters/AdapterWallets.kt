package com.example.vladimir.financetracker.view.adapters

import android.content.Context
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.vladimir.financetracker.Constants
import com.example.vladimir.financetracker.R
import com.example.vladimir.financetracker.databinding.ItemTransactionsListBinding
import com.example.vladimir.financetracker.databinding.ItemWalletsListBinding
import com.example.vladimir.financetracker.model.entity.Transaction
import com.example.vladimir.financetracker.model.entity.Wallet
import com.example.vladimir.financetracker.viewmodel.FinanceTrackerViewModel

class AdapterWallets(viewModel: FinanceTrackerViewModel) : RecyclerView.Adapter<AdapterWallets.ViewHolder>() {

    val mList = mutableListOf<Wallet>()
    val mViewModel = viewModel


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterWallets.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemWalletsListBinding = ItemWalletsListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemWalletsListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(mList[position])
        holder.binding.root.setOnClickListener {
            mViewModel.changeWallet(mList[position].name)
//            holder.binding.root.setBackgroundColor(ResourcesCompat.getColor(holder.binding.root.context.resources, R.color.colorAccent, null))
//            notifyDataSetChanged()
        }
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