package com.example.vladimir.financetracker

import android.annotation.SuppressLint
import android.content.Context

class PreferencesUtils(context: Context) {

    companion object {
        private const val PREF_LANG = "lang"
        private const val PREF_LANG_DEFAULT = "en"
    }

    val prefs = context.getSharedPreferences("conf", Context.MODE_PRIVATE)

    fun getLanguage() = try {
            prefs.getString(PREF_LANG, PREF_LANG_DEFAULT)
        }
        catch (e: Exception) {
            PREF_LANG_DEFAULT
        }

    @SuppressLint("ApplySharedPref")
    fun setLanguage(language: String) {
        prefs
                .edit()
                .putString(PREF_LANG, language)
                .commit()
    }
}