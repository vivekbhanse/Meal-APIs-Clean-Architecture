package com.example.startmvvm.domain.models

import com.example.startmvvm.data.datasource.dto.MealDTO


data class MealResponse(
    val meals: List<MealDTO>
)