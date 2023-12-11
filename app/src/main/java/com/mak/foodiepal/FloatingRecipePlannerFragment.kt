package com.mak.foodiepal

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mak.foodiepal.viewmodels.MealPlannerViewModel

class FloatingRecipePlannerFragment : DialogFragment() {
    private val mealPlanerViewModel by activityViewModels<MealPlannerViewModel>()
    private val data =
        listOf("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.floating_recipe_planner, container, false)

        //Calendar view
        /*val datePicker = view.findViewById<DatePicker>(R.id.datePicker)
        datePicker.updateDate(0, 0, Calendar.MONDAY)

        datePicker.init(
            datePicker.year,
            datePicker.month,
            datePicker.dayOfMonth
        ) { _, year, monthOfYear, dayOfMonth ->
            // Handle the selected date
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, monthOfYear, dayOfMonth)

            // Get the day of the week (e.g., "Monday")
            val dayOfWeek = SimpleDateFormat("EEEE", Locale.getDefault()).format(selectedDate.time)

            Toast.makeText(requireContext(), "Selected: $dayOfWeek", Toast.LENGTH_SHORT).show()
        }*/


        val spinner = view.findViewById<Spinner>(R.id.spDays)

        // Populate the Spinner with data

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, data)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        //Autocomplete
        val recipesSuggestion = getRecipeNames()
        val adapter2 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, recipesSuggestion)
        val autoCompleteTextView = view.findViewById<AutoCompleteTextView>(R.id.acRecipe)
        autoCompleteTextView.setAdapter(adapter2)

        //return inflater.inflate(R.layout.floating_recipe_planner, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btnAdd = view.findViewById<AppCompatButton>(R.id.btnAdd)
        val btnCancel = view.findViewById<AppCompatButton>(R.id.btnCancel)
        val spDay = view.findViewById<Spinner>(R.id.spDays)
        val etRecipeName = view.findViewById<AutoCompleteTextView>(R.id.acRecipe)


        btnAdd.setOnClickListener {
            Log.i("AIUB", spDay.selectedItem.toString())
            Log.i("AIUB", etRecipeName.text.toString())

            updateSharedData(spDay.selectedItem.toString(), etRecipeName.text.toString())
            dismiss()
        }
        btnCancel.setOnClickListener {
            dismiss()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun updateSharedData(day: String, recipeName: String) {
        mealPlanerViewModel.updateSharedData(Pair(day, recipeName))
    }

    private fun retrieveRecipes(context: Context): List<Recipe> {
        val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val jsonString = sharedPreferences.getString("recipes", "")
        val gson = Gson()
        val type = object : TypeToken<List<Recipe>>() {}.type
        return gson.fromJson(jsonString, type) ?: emptyList()
    }

    private fun getRecipeNames(): List<String> {
        return retrieveRecipes(requireContext()).map { it.recipeName.toString() }
    }
}