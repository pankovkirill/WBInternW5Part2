package com.example.wbinternw5part2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wbinternw5part2.model.data.AppState
import com.example.wbinternw5part2.model.data.DataModel
import com.example.wbinternw5part2.model.repository.Repository
import com.example.wbinternw5part2.model.repository.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HeroViewModel(
    private val repository: Repository<DataModel> = RepositoryImpl()
) : ViewModel() {
    private val _data = MutableLiveData<AppState>()

    val liveDataForViewToObserve: LiveData<AppState> = _data

    fun getData(name: String) {
        _data.postValue(AppState.Loading)
        viewModelScope.launch { loadData(name) }
    }

    private suspend fun loadData(name: String) = withContext(Dispatchers.IO) {
        try {
            _data.postValue(AppState.Success(repository.getData(name)))
        } catch (e: Throwable) {
            _data.postValue(AppState.Error(e))
        }
    }
}