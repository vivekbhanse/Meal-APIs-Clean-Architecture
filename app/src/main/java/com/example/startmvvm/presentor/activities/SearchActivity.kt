package com.example.startmvvm.presentor.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.startmvvm.R
import com.example.startmvvm.databinding.ActivityMainBinding
import com.example.startmvvm.databinding.ActivitySearchBinding
import com.example.startmvvm.presentor.viewmodel.SearchViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private var _binding: ActivitySearchBinding?=null
    private val binding get()=_binding!!
    private val searchViewModel:SearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=DataBindingUtil.setContentView(this,R.layout.activity_search)
        searchViewModel.getSearchMeal("p")
        CoroutineScope(IO).launch {
            searchViewModel.searchValue.collectLatest {
                if (it.mealList.isNotEmpty()) {
                    withContext(Main){
                        binding.txtData.text=it.mealList.toString()
                    }

                }else{

                }
            }
        }
    }
}