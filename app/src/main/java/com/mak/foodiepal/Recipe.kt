package com.mak.foodiepal

import java.io.Serializable

class Recipe(
    val recipeName: String?,
    val ingredients: List<String?>,
    val instruction: String?
) : Serializable {
    override fun toString(): String {
        return "Recipe(recipeName=$recipeName, ingredients=$ingredients, instruction=$instruction)"
    }
}