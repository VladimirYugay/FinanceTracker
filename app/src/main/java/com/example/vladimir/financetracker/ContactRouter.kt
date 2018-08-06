package com.example.vladimir.financetracker

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

class ContactRouter(private val context: Context) {

    companion object {
        private const val packageNameVk = "com.vkontakte.android"
        private const val packageNameTg = "org.telegram.messenger"
        private const val packageNameGmail = "com.google.android.gm"

        private const val contactVk = "https://vk.com/id54957320"
        private const val contactTg= "https://t.me/luigivampa92"
        private const val contactEmail = "luigivampa92@gmail.com"
    }

    fun openVkContactPage(pageUrl: String = contactVk) {
        val contactIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl))
        var vkAppFound = false

        val otherApps = context.packageManager.queryIntentActivities(contactIntent, 0)
        for (otherApp in otherApps) {
            if (otherApp.activityInfo.applicationInfo.packageName == packageNameVk) {

                val otherAppActivity = otherApp.activityInfo
                val componentName = ComponentName(
                        otherAppActivity.applicationInfo.packageName,
                        otherAppActivity.name
                )
                contactIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                contactIntent.component = componentName
                context.startActivity(contactIntent)
                vkAppFound = true
                break

            }
        }

        if (!vkAppFound) {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl))
            webIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(webIntent)
        }
    }

    fun openTgContactPage(pageUrl: String = contactTg) {
        val contactIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl))
        var tgAppFound = false

        val otherApps = context.packageManager.queryIntentActivities(contactIntent, 0)
        for (otherApp in otherApps) {
            if (otherApp.activityInfo.applicationInfo.packageName == packageNameTg) {

                val otherAppActivity = otherApp.activityInfo
                val componentName = ComponentName(
                        otherAppActivity.applicationInfo.packageName,
                        otherAppActivity.name
                )
                contactIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                contactIntent.component = componentName
                context.startActivity(contactIntent)
                tgAppFound = true
                break

            }
        }

        if (!tgAppFound) {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(pageUrl))
            webIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(webIntent)
        }
    }

    fun openSendEmailPage(email: String = contactEmail) {
        val contactIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
        var gmailAppFound = false

        val otherApps = context.packageManager.queryIntentActivities(contactIntent, 0)
        for (otherApp in otherApps) {
            if (otherApp.activityInfo.applicationInfo.packageName == packageNameGmail) {
                val otherAppActivity = otherApp.activityInfo
                val componentName = ComponentName(otherAppActivity.applicationInfo.packageName, otherAppActivity.name)
                contactIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                contactIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.text_info_about_app_email_subject))
                contactIntent.component = componentName
                context.startActivity(contactIntent)
                gmailAppFound = true
                break
            }
        }

        if (!gmailAppFound) {
            val directMailtoIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email))
            directMailtoIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            directMailtoIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.text_info_about_app_email_subject))
            try {
                context.startActivity(directMailtoIntent)
            }
            catch (e: ActivityNotFoundException) {
                Toast.makeText(context, R.string.text_info_about_app_email_no_client, Toast.LENGTH_SHORT).show()
            }
        }
    }
}