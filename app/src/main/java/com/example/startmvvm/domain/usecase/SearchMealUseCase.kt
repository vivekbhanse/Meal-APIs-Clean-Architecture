package com.example.startmvvm.domain.usecase

import com.example.startmvvm.data.datasource.MealAPI
import com.example.startmvvm.domain.models.MealResponse
import com.example.startmvvm.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class SearchMealUseCase @Inject constructor(private val mealAPI: MealAPI) {
    operator fun invoke(searchLetter:String): Flow<ResponseState<List<MealResponse>>> = flow {
        try {
            emit(ResponseState.Loading<List<MealResponse>>())
            val searchResult=mealAPI.searchMealRequest(searchLetter).toMealResponse()
            emit(ResponseState.Success<List<MealResponse>>(listOf(searchResult)))
        }catch (e:Exception){
            emit(ResponseState.Error<List<MealResponse>>(msg = "Error Occured"))
        }


    }
}