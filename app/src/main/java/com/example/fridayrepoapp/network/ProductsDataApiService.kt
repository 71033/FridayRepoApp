package com.example.fridayrepoapp.network

import com.example.fridayrepoapp.data.ProductsData
import retrofit2.http.GET

interface ProductsDataApiService {
    @GET("/products")
    suspend fun getProductsData(): ProductsData
}

