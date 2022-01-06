package com.qourall.yogaapp.ui.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.qourall.yogaapp.YogaApplication
import com.qourall.yogaapp.data.localDB.Category
import com.qourall.yogaapp.repository.YogaRepository
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = YogaRepository(application as YogaApplication)

    private var _allCat = MutableLiveData<List<Category>>()

    val allCat : LiveData<List<Category>>
        get() = _allCat

    init {
        getAllCategory()
    }

    private fun getAllCategory() {
        viewModelScope.launch {
            _allCat.value =  repo.getAllCat()
        }
    }

}