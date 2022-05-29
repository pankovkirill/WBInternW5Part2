package com.example.wbinternw5part2.model.repository

import com.example.wbinternw5part2.model.data.DataModel
import com.example.wbinternw5part2.model.datasource.DataSource
import com.example.wbinternw5part2.model.datasource.RetrofitImpl

class RepositoryImpl(
    private val dataSource: DataSource<DataModel> = RetrofitImpl()
) : Repository<DataModel> {
    override suspend fun getData(name: String): DataModel {
        return dataSource.getData(name)
    }
}