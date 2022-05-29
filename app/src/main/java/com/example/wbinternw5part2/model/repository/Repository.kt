package com.example.wbinternw5part2.model.repository

interface Repository<T> {
    suspend fun getData(name: String): T
}