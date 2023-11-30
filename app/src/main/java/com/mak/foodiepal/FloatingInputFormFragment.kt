package com.mak.foodiepal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment

class FloatingInputFormFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.floating_input_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAdd = view.findViewById<AppCompatButton>(R.id.btnAdd)
        val btnCancel = view.findViewById<AppCompatButton>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            dismiss()
        }
        btnAdd.setOnClickListener {
                /*view ->
            Snackbar.make(view, "Button clicked", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
        }
    }
}