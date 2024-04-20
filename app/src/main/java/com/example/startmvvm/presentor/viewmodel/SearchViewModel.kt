package com.example.startmvvm.presentor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startmvvm.domain.usecase.SearchMealUseCase
import com.example.startmvvm.presentor.MealState
import com.example.startmvvm.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchMealUseCase: SearchMealUseCase):ViewModel(){
    private val _searchValue=MutableStateFlow(MealState())
     val searchValue=_searchValue

    fun getSearchMeal(serachLetter:String)=viewModelScope.launch(IO) {
        searchMealUseCase.invoke(serachLetter).collectLatest {
            when(it){
                is ResponseState.Loading->{
                    _searchValue.value=MealState(isLoading = true)
                }
                is ResponseState.Success->{
                    _searchValue.value= MealState(mealList = it.data!!)
                }
                is ResponseState.Error->{
                    _searchValue.value= MealState(error = "Error Occured")
                }

                else -> {}
            }
        }
    }

}

