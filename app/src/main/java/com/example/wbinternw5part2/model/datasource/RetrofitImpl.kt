package com.example.wbinternw5part2.model.datasource

import com.example.wbinternw5part2.model.data.ApiService
import com.example.wbinternw5part2.model.data.DataModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl : DataSource<DataModel> {
    override suspend fun getData(name: String): DataModel {
        return getService().getDataAsync(name).await()
    }

    private fun getService(): ApiService {
        return createRetrofit().create(ApiService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    companion object {
        private const val BASE_URL = "https://www.superheroapi.com/api.php/403576745111910/"
    }
}