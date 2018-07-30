package com.example.vladimir.financetracker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
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