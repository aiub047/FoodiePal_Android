package com.mak.foodiepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mak.foodiepal.databinding.ActivityHolderBinding

class HolderActivity : AppCompatActivity() {

    //lateinit var fmanager: FragmentManager
    //lateinit var tx: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holder)

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
        fab.setOnClickListener {
            //showToastMessage("Working");
            /*view ->
        Snackbar.make(view, "Button clicked", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()*/

            val inputFormFragment = FloatingInputFormFragment()
            inputFormFragment.show(supportFragmentManager, "FloatingInputFormFragment")
        }


        /*fmanager = supportFragmentManager
        tx = fmanager.beginTransaction()
        //tx.add(R.id.flHolder, Recipes())
        tx.add(R.id.vpHolder, Recipes())*/

    }


    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mnRecipes -> {
                showToastMessage("Recipes selected.")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }*/

    fun showToastMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }
}