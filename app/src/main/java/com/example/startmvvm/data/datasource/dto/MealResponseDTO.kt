package com.example.startmvvm.data.datasource.dto

import com.example.startmvvm.domain.models.MealResponse

data class MealResponseDTO(
    val meals: List<MealDTO>
){
    fun toMealResponse(): MealResponse {
        return MealResponse(
            meals=meals
        )
    }
}