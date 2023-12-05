package com.mak.foodiepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mak.foodiepal.databinding.ActivityHolderBinding
import com.mak.foodiepal.viewmodels.RecipeViewModel

class HolderActivity : AppCompatActivity(), FloatingInputFormFragment.OnItemAddedListener {

    private lateinit var reciepeViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holder)
        reciepeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        var selectedPosition = 0

        val vpHolder = findViewById<ViewPager2>(R.id.vpHolder)
        val tlTopMenu = findViewById<TabLayout>(R.id.tlTopMenu)

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

    override fun onItemAdded(item: Recipe) {
        Log.e("AIUB", "${item.recipeName} STEP 1")
        reciepeViewModel.setRecipe(item)
        reciepeViewModel.setTitle("Hilaly")
    }
}