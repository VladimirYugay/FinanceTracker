package com.example.vladimir.financetracker.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.vladimir.financetracker.ContactRouter
import com.example.vladimir.financetracker.R

class FragmentAbout : Fragment() {

    private lateinit var contactRouter : ContactRouter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_about, container, false).also {
                contactRouter = ContactRouter(context!!)
                it.findViewById<Toolbar>(R.id.activity_about_toolbar).setNavigationOnClickListener {
                    fragmentManager?.popBackStack()
                }
                it.findViewById<ImageButton>(R.id.button_contact_email).setOnClickListener {
                    contactRouter.openSendEmailPage()
                }
                it.findViewById<ImageButton>(R.id.button_contact_vk).setOnClickListener {
                    contactRouter.openVkContactPage()
                }
                it.findViewById<ImageButton>(R.id.button_contact_tg).setOnClickListener {
                    contactRouter.openTgContactPage()
                }
            }
}