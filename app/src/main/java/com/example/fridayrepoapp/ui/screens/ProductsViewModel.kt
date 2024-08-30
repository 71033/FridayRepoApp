package com.example.fridayrepoapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.fridayrepoapp.MyApplication
import com.example.fridayrepoapp.data.ProductsData
import com.example.fridayrepoapp.data.ProductsDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class AbcViewModel : ViewModel (){

}


class ProductsViewModel(private val repo: ProductsDataRepository): ViewModel() {
    private val _productsData = MutableStateFlow<ProductsData?>(null)
    val productsData = _productsData.asStateFlow()

    init {
        viewModelScope.launch {

                _productsData.value = repo.getProductsData()
        }
    }

    companion object{
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MyApplication)
                val repo = application.appContainer.productsDataRepository
                ProductsViewModel(repo)
            }
        }
    }


}