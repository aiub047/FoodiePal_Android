package com.mak.foodiepal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ContactFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contact, container, false)

        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
        val tvPhone = view.findViewById<TextView>(R.id.tvPhone)

        val ivCallIcon = view.findViewById<ImageView>(R.id.ivCallIcon)
        val ivTextIcon = view.findViewById<ImageView>(R.id.ivTextIcon)
        val ivEmailIcon = view.findViewById<ImageView>(R.id.ivEmailIcon)

        ivCallIcon.setOnClickListener {
            val phoneNumber = tvPhone.text.toString()
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(callIntent)
        }

        ivTextIcon.setOnClickListener {
            val phoneNumber = tvPhone.text.toString()
            val smsUri = Uri.parse("smsto:$phoneNumber")
            val smsIntent = Intent(Intent.ACTION_VIEW, smsUri)
            smsIntent.putExtra("sms_body", "Hello, thank you so much from FoodiePal.")
            startActivity(smsIntent)
        }

        ivEmailIcon.setOnClickListener {
            val emailAddress = tvEmail.text.toString()
            val subject = "Thank you"
            val body = "Hey, this is a thank you email from FoodiePal app"

            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:$emailAddress")
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            emailIntent.putExtra(Intent.EXTRA_TEXT, body)

            startActivity(Intent.createChooser(emailIntent, "Send Email"))
        }

        return view
    }
}