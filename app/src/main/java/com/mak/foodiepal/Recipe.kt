package com.mak.foodiepal

import java.io.Serializable

class Recipe(
    val recipeName: String?,
    val ingradiants: List<String?>,
    val instrcution: String?
) : Serializable {
    override fun toString(): String {
        return "Recipe(recipeName=$recipeName, ingradiants=$ingradiants, instrcution=$instrcution)"
    }
}