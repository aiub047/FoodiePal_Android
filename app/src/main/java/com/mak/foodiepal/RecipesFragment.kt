package com.mak.foodiepal

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.mak.foodiepal.databinding.FragmentRecipesBinding
import com.mak.foodiepal.viewmodels.RecipeViewModel

class RecipesFragment : Fragment() {
    private val recipeViewModel: RecipeViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private lateinit var binding: FragmentRecipesBinding

    val recipes = ArrayList<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRecipes()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_recipes, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = RecipeAdapter(requireActivity(), getRecipeList())
        recyclerView.adapter = adapter

        binding = FragmentRecipesBinding.bind(view);
        binding.recyclerView.adapter = adapter

        recipeViewModel.recipeObserver.observe(viewLifecycleOwner, Observer {
            addRecipe(it)
        })

        return view
    }

    private fun getRecipeList(): List<Recipe> {
        return recipes
    }

    private fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
        saveSharedPreference(requireContext())
    }

    private fun setRecipes() {
        recipes.add(
            Recipe(
                "Margherita Pizza",
                listOf("Pizza dough", "Tomato sauce", "Mozzarella"),
                "Instructions..."
            )
        )
        recipes.add(
            Recipe(
                "Chocolate Cake",
                listOf("Flour", "Sugar", "Cocoa powder"),
                "Instructions..."
            )
        )
        recipes.add(
            Recipe(
                "Banana Cake",
                listOf("Banana", "Sugar", "Milk powder"),
                "Instructions..."
            )
        )
        saveSharedPreference(requireContext())
    }

    fun getRecipes(): List<Recipe> {
        return recipes
    }

    private fun saveSharedPreference(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val jsonString = gson.toJson(recipes)
        editor.putString("recipes", jsonString)
        editor.apply()
    }
}