package com.example.fridayrepoapp.data

import com.example.fridayrepoapp.network.ProductsDataApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val productsDataRepository: ProductsDataRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://dummyjson.com/products/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: ProductsDataApiService by lazy {
        retrofit.create(ProductsDataApiService::class.java)
    }

    override val productsDataRepository: ProductsDataRepository by lazy {
        NetworkProductsDataRepository(retrofitService)
    }

}
