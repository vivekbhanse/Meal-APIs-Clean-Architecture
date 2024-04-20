package com.example.startmvvm.data.datasource.dto

import com.example.startmvvm.domain.models.CategoriesModel

data class CategoriesDTO(
    val categories: List<Category>
) {
fun toCategoriesModel():CategoriesModel{
    return CategoriesModel(
        categories = categories
    )
}
}