package com.mak.foodiepal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mak.foodiepal.databinding.FragmentRecipesBinding
import com.mak.foodiepal.viewmodels.RecipeViewModel

class RecipesFragment : Fragment() {

    private lateinit var recipeViewModel: RecipeViewModel

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
        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = RecipeAdapter(requireActivity(), getRecipeList())
        recyclerView.adapter = adapter

        binding = FragmentRecipesBinding.bind(view);
        //binding.tvRecipeHeader.text = "This is my text view"
        binding.recyclerView.adapter = adapter

        recipeViewModel.recipeObserver.observe(viewLifecycleOwner, Observer {
            Log.e("AIUB", "${it.recipeName} STEP 3")
        })

        recipeViewModel.title.observe(viewLifecycleOwner, Observer {
            Log.e("AIUB", "${it} STEP 3.3")
        })
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