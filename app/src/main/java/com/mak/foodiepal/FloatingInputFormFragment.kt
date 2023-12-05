package com.mak.foodiepal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment

class FloatingInputFormFragment : DialogFragment() {

    interface OnItemAddedListener {
        fun onItemAdded(item: Recipe)
    }

    private var itemAddedListener: OnItemAddedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.floating_input_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val edtRecipeName = view.findViewById<EditText>(R.id.edRecipeName)
        val btnAdd = view.findViewById<AppCompatButton>(R.id.btnAdd)
        val btnCancel = view.findViewById<AppCompatButton>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            dismiss()
        }
        btnAdd.setOnClickListener {
            Log.d("AIUB", "2. setOnClickListener")
            val recipe = Recipe(edtRecipeName.text.toString(), listOf("Test 1"), null)
            itemAddedListener?.onItemAdded(recipe)
            dismiss()
            /*view ->
        Snackbar.make(view, "Button clicked", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()*/


        }
    }

    fun setOnItemAddedListener(listener: OnItemAddedListener) {
        Log.d("AIUB", "1. setOnItemAddedListener")
        itemAddedListener = listener
    }
}