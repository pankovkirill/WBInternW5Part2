package com.example.wbinternw5part2.model.datasource

interface DataSource<T> {
    suspend fun getData(name: String): T
}