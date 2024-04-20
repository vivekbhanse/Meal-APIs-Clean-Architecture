package com.example.startmvvm.presentor

import com.example.startmvvm.domain.models.MealResponse
import com.example.startmvvm.utils.ResponseState

data class MealState(
    val isLoading: Boolean=false,
    val mealList: List<MealResponse> = emptyList(),
    val error:String=""
)