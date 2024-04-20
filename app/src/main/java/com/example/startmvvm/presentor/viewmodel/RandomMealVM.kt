package com.example.startmvvm.presentor.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.startmvvm.data.datasource.dto.CategoriesDTO
import com.example.startmvvm.domain.models.Meal
import com.example.startmvvm.domain.usecase.CategoryMealUseCase
import com.example.startmvvm.domain.usecase.RandomMealUseCase
import com.example.startmvvm.presentor.CategoryState
import com.example.startmvvm.presentor.MealState
import com.example.startmvvm.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RandomMealVM @Inject constructor(
     val randomMealUseCase: RandomMealUseCase,
     val categoryMealUseCase: CategoryMealUseCase
) :ViewModel(){
   // private val _users = MutableStateFlow<List<MealResponse>>(emptyList())
   // val users get() = _users.asStateFlow()
    var mealvalue=MutableStateFlow(MealState())
    var mealvalueCat=MutableStateFlow(CategoryState())
    val _mealvalue:StateFlow<MealState> =mealvalue
    val _mealvaluecat:StateFlow<CategoryState> =mealvalueCat
    var resultImageUrl = ObservableField<String>()



    fun getAllMeals(startWith:String)=viewModelScope.launch (Dispatchers.IO){
        randomMealUseCase.invoke(startWith).collectLatest {
            when (it) {
                is ResponseState.Success-> {
                    mealvalue.value= MealState(mealList = it.data!!)
                    resultImageUrl.set(it.data.toString())

                }

                is ResponseState.Loading->{
                    mealvalue.value= MealState( isLoading = true)
                }

                is ResponseState.Error->{
                    mealvalue.value= MealState(error=it.msg?:"An Error")
                }
            }
        }
    }

    fun getCategoryMeal(startWith:String)=viewModelScope.launch (Dispatchers.IO){
        categoryMealUseCase.invoke("").collectLatest {
            when(it){
                is ResponseState.Success ->{
                    mealvalueCat.value= CategoryState(mealList = it.data!!)
                }
                is ResponseState.Loading ->{
                    mealvalueCat.value= CategoryState(isLoading = true)
                }
                is ResponseState.Error ->{
                    mealvalueCat.value= CategoryState(error = it.msg?:"Error Coming")
                }
            }
        }
    }

    fun imageUrlUpdated(url: String?) {
        resultImageUrl.set(url)
    }

    fun getRefresh(){
        getAllMeals("p")
    }

//   companion object{
//       @JvmStatic
//       @BindingAdapter("imageUrl")
//       fun loadImage(view: ImageView, url: String) {
//           if (!url.isNullOrEmpty()) {
//               Glide.with(view.context).load(url).into(view)
//           }
//       }
//   }


}


