package com.example.startmvvm.data.repository

import com.example.startmvvm.data.datasource.MealAPI
import com.example.startmvvm.data.datasource.dto.CategoriesDTO
import com.example.startmvvm.data.datasource.dto.MealResponseDTO
import javax.inject.Inject

class MealRepoImpl @Inject constructor(val mealAPI: MealAPI): MealAPI {
    override suspend fun randomMealRequest(startWith: String): MealResponseDTO {
        return mealAPI.randomMealRequest(startWith)
    }

    override suspend fun searchMealRequest(searchLetter: String): MealResponseDTO {
        return mealAPI.searchMealRequest(searchLetter)
    }

    override suspend fun SearchCategoriesMeal(searchLetter: String): CategoriesDTO {
        return mealAPI.SearchCategoriesMeal("")
    }
}