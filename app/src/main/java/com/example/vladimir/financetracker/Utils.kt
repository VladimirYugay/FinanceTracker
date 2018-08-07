package com.example.vladimir.financetracker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AlertDialog
import com.example.vladimir.financetracker.view.activities.ActivityMain
import java.text.DecimalFormat
import java.util.*
import java.util.UUID.randomUUID
import java.util.Collections.replaceAll



fun formatNumber(number: Double): String {
    return DecimalFormat("#.00").format(number)
}

fun createId(): String {
    return UUID.randomUUID().toString().toUpperCase()
}

fun goTo(context: Context?, fragmentName: String){
    val intent = Intent(context, ActivityMain::class.java)
    intent.putExtra(Routes.DESTINATION_FRAGMENT, fragmentName)
    context?.startActivity(intent)
}

fun getString(@StringRes id: Int) = FinanceTrackerApplication.INSTANCE.getString(id)

fun alertError(context: Context, message: String) {
    AlertDialog.Builder(context)
            .setTitle("Ошибка")
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("ОК", { dialog, _ -> dialog.dismiss() })
            .show()
}

fun alertError(context: Context, @StringRes id: Int) {
    alertError(context, getString(id))
}

fun Double.fmtMoney() = String.format("%.2f", this).replace(",", ".")
