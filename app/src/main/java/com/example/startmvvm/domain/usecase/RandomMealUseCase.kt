package com.example.startmvvm.domain.usecase

import com.example.startmvvm.data.datasource.MealAPI
import com.example.startmvvm.data.datasource.dto.MealResponseDTO
import com.example.startmvvm.domain.models.MealResponse

import com.example.startmvvm.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class RandomMealUseCase @Inject constructor(
    val repository: MealAPI)
{
    operator fun invoke(startWith: String) : Flow<ResponseState<List<MealResponse>>> = flow {
        try {
            emit(ResponseState.Loading<List<MealResponse>>())
            val mealsResponse=repository.randomMealRequest("").toMealResponse()
            emit(ResponseState.Success<List<MealResponse>>(listOf(mealsResponse)))
        }catch (e:Exception){
            e.printStackTrace()
            emit(ResponseState.Error<List<MealResponse>>(null,e.localizedMessage))
        }
    }

}