package com.mak.foodiepal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mak.foodiepal.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private lateinit var binding: FragmentRecipesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        binding.tvTest.text = "This is my text view"
        binding.recyclerView.adapter = adapter

        return view
    }

    private fun getRecipeList(): List<Recipe> {
        val recipes = ArrayList<Recipe>()
        recipes.add(Recipe("Pasta Carbonara", listOf("Pasta", "Eggs", "Bacon"), "Instructions..."))
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
        return recipes
    }
}