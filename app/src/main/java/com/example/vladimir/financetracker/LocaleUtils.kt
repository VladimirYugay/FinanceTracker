package com.example.vladimir.financetracker

import android.content.Context
import android.content.res.Configuration
import java.util.*

class LocaleUtils(private val context: Context) {

    private val prefs = PreferencesUtils(context)

    fun setupLocale() {
        setNewLocale(getLanguage())
    }

    fun setNewLocale(language: String) {
        persistLanguage(language)
        updateResources(language)
    }

    fun getLanguage() = prefs.getLanguage()

    fun persistLanguage(language: String) {
        prefs.setLanguage(language)
    }

    fun updateResources(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val res = context.resources
        val config = Configuration(res.configuration)
        config.locale = locale
        res.updateConfiguration(config, res.displayMetrics)
    }
}
