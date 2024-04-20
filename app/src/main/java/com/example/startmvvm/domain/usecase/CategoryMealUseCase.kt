package com.example.startmvvm.domain.usecase

import com.example.startmvvm.data.datasource.MealAPI
import com.example.startmvvm.domain.models.CategoriesModel
import com.example.startmvvm.domain.models.MealResponse
import com.example.startmvvm.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class CategoryMealUseCase @Inject constructor(val mealAPI: MealAPI) {
    operator fun invoke(category: String): Flow<ResponseState<List<CategoriesModel>>> = flow {
        try {
            emit(ResponseState.Loading())
            val categoryResponse = mealAPI.SearchCategoriesMeal(category).toCategoriesModel()
            emit(ResponseState.Success<List<CategoriesModel>>(listOf(categoryResponse)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResponseState.Error<List<CategoriesModel>>(null, e.localizedMessage))
        }
    }
}