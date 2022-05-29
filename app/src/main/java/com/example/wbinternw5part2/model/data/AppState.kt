package com.example.wbinternw5part2.model.data

sealed class AppState {
    data class Success(val dataModel: DataModel?) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
