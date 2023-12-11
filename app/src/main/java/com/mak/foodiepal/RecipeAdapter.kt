package com.mak.foodiepal

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(private val context: Context, private val recipeList: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvRecipeName: TextView
        var tvItemInfo: TextView
        var tvItemDesc: TextView

        init {
            tvRecipeName = itemView.findViewById(R.id.tvRecipeName)
            tvItemInfo = itemView.findViewById(R.id.tvItemInfo)
            tvItemDesc = itemView.findViewById(R.id.tvItemDesc)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_recipe, parent, false)
        val layoutParams = view.layoutParams
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        view.layoutParams = layoutParams
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeViewHolder, position: Int) {
        val currentItem = recipeList[position]
        holder.tvRecipeName.text = currentItem.recipeName
        holder.tvRecipeName.setTypeface(null, Typeface.BOLD)
        holder.tvItemInfo.text = currentItem.ingredients.joinToString("\n")
        holder.tvItemDesc.text = currentItem.instruction
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}