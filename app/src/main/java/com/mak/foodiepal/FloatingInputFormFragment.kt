package com.mak.foodiepal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

        val etRecipeName = view.findViewById<EditText>(R.id.etRecipeName)
        val etIngredients = view.findViewById<EditText>(R.id.etIngredients)
        val etInstructions = view.findViewById<EditText>(R.id.etInstructions)


        val btnAdd = view.findViewById<AppCompatButton>(R.id.btnAdd)
        val btnCancel = view.findViewById<AppCompatButton>(R.id.btnCancel)

        btnCancel.setOnClickListener {
            dismiss()
        }
        btnAdd.setOnClickListener {
            val ingredients: List<String> = etIngredients.text.lines()
                .filter { it.isNotBlank() }
            val recipe =
                Recipe(etRecipeName.text.toString(), ingredients, etInstructions.text.toString())
            itemAddedListener?.onItemAdded(recipe)
            dismiss()
            /*view ->
        Snackbar.make(view, "Button clicked", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()*/
        }
    }

    fun setOnItemAddedListener(listener: OnItemAddedListener) {
        itemAddedListener = listener
    }
}