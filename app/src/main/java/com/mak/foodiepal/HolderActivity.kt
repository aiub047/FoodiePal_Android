package com.mak.foodiepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mak.foodiepal.databinding.ActivityHolderBinding
import com.mak.foodiepal.viewmodels.RecipeViewModel

class HolderActivity : AppCompatActivity(), FloatingInputFormFragment.OnItemAddedListener {

    private lateinit var binding: ActivityHolderBinding

    private val recipeViewModel: RecipeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var selectedPosition = 0

        val vpHolder = binding.vpHolder
        val tlTopMenu = binding.tlTopMenu

        val vpAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        vpHolder.adapter = vpAdapter

        TabLayoutMediator(tlTopMenu, vpHolder) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Recipes"
                    tab.setIcon(R.drawable.baseline_fastfood_24)
                }

                1 -> {
                    tab.text = "Meal Planner"
                    tab.setIcon(R.drawable.baseline_set_meal_24)
                }

                2 -> {
                    tab.text = "Blog"
                    tab.setIcon(R.drawable.baseline_blog_24)
                }

                3 -> {
                    tab.text = "Contact"
                    tab.setIcon(R.drawable.baseline_contact_calendar_24)
                }

                4 -> {
                    tab.text = "About Me"
                    tab.setIcon(R.drawable.baseline_person_24)
                }
            }
        }.attach()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { /*view ->
            Snackbar.make(view, "Floating button clicked", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/

            when (selectedPosition) {
                0 -> {
                    showRecipesForm()
                }

                1 -> {
                    showRecipePlannerForm()
                }
            }
        }


        vpHolder.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                //val currentlyVisibleFragment = vpAdapter.getFragmentAt(position)
                //fragmentName = currentlyVisibleFragment.javaClass.simpleName
                selectedPosition = position
            }
        })


    }

    private fun showRecipesForm() {
        val inputFormFragment = FloatingInputFormFragment()
        inputFormFragment.setOnItemAddedListener(this)
        inputFormFragment.show(supportFragmentManager, "FloatingInputFormFragment")
    }

    private fun showRecipePlannerForm() {
        val inputFormFragment = FloatingRecipePlannerFragment()
        //inputFormFragment.setOnItemAddedListener(this)
        inputFormFragment.show(supportFragmentManager, "FloatingRecipePlannerFragment")
    }

    override fun onItemAdded(item: Recipe) {
        //Set data in RecipeViewModel so that observers can get the data
        recipeViewModel.setRecipe(item)
    }
}