package com.mak.foodiepal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mak.foodiepal.databinding.FragmentMealPlannerBinding
import com.mak.foodiepal.viewmodels.MealPlannerViewModel

class MealPlannerFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlannerAdapter
    private lateinit var binding: FragmentMealPlannerBinding
    private lateinit var weeklyPlan: MutableList<Pair<String, String>>
    private val mealPlanerViewModel by activityViewModels<MealPlannerViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        addSampleData()
        adapter = PlannerAdapter(requireActivity(), getWeeklyPlan())
        recyclerView.adapter = adapter

        mealPlanerViewModel.sharedData.observe(viewLifecycleOwner, Observer { newData ->
            run {
                addWeeklyPlan(newData.first, newData.second)
            }
        })

        return view
    }

    private fun getWeeklyPlan(): MutableList<Pair<String, String>> {
        return weeklyPlan
        /*return mutableListOf(
            Pair("Monday", "Margherita Pizza"),
            Pair("Tuesday", "Banana Cake"),
            Pair("Wednesday", "Client Call"),
            Pair("Thursday", "Work on Project B"),
            Pair("Friday", "Submit Reports"),
            Pair("Saturday", "Family Time"),
            Pair("Sunday", "Relax and Rest")
        )*/
    }

    private fun addSampleData() {
        weeklyPlan = mutableListOf()
        weeklyPlan.add(Pair("Sunday", "Chocolate Cake"))
        weeklyPlan.add(Pair("Monday", "Margherita Pizza"))
    }

    fun addWeeklyPlan(day: String, recipeName: String) {
        weeklyPlan.add(Pair(day, recipeName))
    }

    /* fun setOnItemAddedListener(listener: FloatingInputFormFragment.OnItemAddedListener) {
            itemAddedListener = listener
        }*/
}