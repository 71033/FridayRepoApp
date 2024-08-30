package com.example.fridayrepoapp.data

import com.example.fridayrepoapp.network.ProductsDataApiService

interface ProductsDataRepository {
    suspend fun getProductsData(): ProductsData
}

class NetworkProductsDataRepository(private val retrofitService: ProductsDataApiService) : ProductsDataRepository {
    override suspend fun getProductsData(): ProductsData {
        return retrofitService.getProductsData()
    }
}
