package com.example.vladimir.financetracker

import android.content.Context
import android.content.Intent
import android.support.annotation.ArrayRes
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.inputmethod.InputMethodManager
import com.example.vladimir.financetracker.view.activities.ActivityMain

fun goTo(context: Context?, fragmentName: String){
    val intent = Intent(context, ActivityMain::class.java)
    intent.putExtra(Routes.DESTINATION_FRAGMENT, fragmentName)
    context?.startActivity(intent)
}

fun getString(@StringRes id: Int) = FinanceTrackerApplication.INSTANCE.getString(id)

fun getStringArray(@ArrayRes id: Int) = FinanceTrackerApplication.INSTANCE.resources.getStringArray(id)

fun getColor(@ColorRes id: Int) = ContextCompat.getColor(FinanceTrackerApplication.INSTANCE, id)

fun alertError(context: Context, message: String) {
    AlertDialog.Builder(context)
            .setTitle(getString(R.string.text_error))
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.text_ok, { dialog, _ -> dialog.dismiss() })
            .show()
}

fun alertError(context: Context, @StringRes id: Int) {
    alertError(context, getString(id))
}

fun Double.fmtMoney(): String {
    var value = String.format("%.2f", Math.abs(this)).replace(",", ".")
    if (this > 0) {
        value = "+ " + value
    }
    else if (this < 0) {
        value = "- " + value
    }
    return value
}

fun Fragment.hideSoftKeyboard() {
    activity?.let {
        activity!!.currentFocus?.let {
            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}