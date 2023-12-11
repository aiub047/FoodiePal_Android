package com.mak.foodiepal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlannerAdapter(
    private val context: Context,
    private val weeklyRecipeList: MutableList<Pair<String, String>>
) :
    RecyclerView.Adapter<PlannerAdapter.PlannerViewHolder>() {

    inner class PlannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvWeekDay: TextView
        var tvRecipeName: TextView
        var ivDeleteIcon: ImageView

        init {
            tvWeekDay = itemView.findViewById(R.id.tvWeekDay)
            tvRecipeName = itemView.findViewById(R.id.tvRecipeName)
            ivDeleteIcon = itemView.findViewById(R.id.ivDeleteIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlannerViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_planner_item, parent, false)
        val layoutParams = view.layoutParams
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        view.layoutParams = layoutParams
        return PlannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlannerViewHolder, position: Int) {
        val currentItem = weeklyRecipeList[position]
        holder.tvWeekDay.text = currentItem.first
        holder.tvRecipeName.text = currentItem.second
        holder.itemView.tag = position
    }

    override fun getItemCount(): Int {
        return weeklyRecipeList.size
    }
}