package com.example.startmvvm.presentor

import com.example.startmvvm.domain.models.CategoriesModel
import com.example.startmvvm.domain.models.MealResponse
import com.example.startmvvm.utils.ResponseState

data class CategoryState(
    val isLoading: Boolean=false,
    val mealList: List<CategoriesModel> = emptyList(),
    val error:String=""
)