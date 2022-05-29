package com.example.wbinternw5part2.model.data

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("search/{name}")
    fun getDataAsync(@Path("name") name: String): Deferred<DataModel>
}