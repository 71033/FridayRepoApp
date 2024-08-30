package com.example.fridayrepoapp.ui.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.fridayrepoapp.data.Product
import com.google.gson.Gson


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val productsDataViewModel: ProductsViewModel = viewModel(factory = ProductsViewModel.factory)
    val productsData by productsDataViewModel.productsData.collectAsState()
    ProductsList(products = productsData?.products ?: emptyList(), navController = navController)
}

@Composable
fun ProductsList(
    modifier: Modifier = Modifier,
    products: List<Product>,
    navController: NavHostController
) {
    LazyColumn {
        items(products){
            ProductCard(product = it, navController = navController)
        }
    }
}

@Composable
fun ProductCard(product: Product, navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()

    ) {
        val productJson = Uri.encode(Gson().toJson(product))
        ElevatedCard(
            onClick = {
                navController.navigate("detailScreen/$productJson")
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFFF8F8F8)),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = product.thumbnail, // Replace with your image URL
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = product.title ?: "No Title",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp))
                RatingStars(rating = product.rating?.toInt() ?: 0)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$ ${product.price}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .weight(1f)
                    )
                    FloatingActionButton(
                        onClick = { /* Add to cart logic */ },
                        containerColor = Color(0xFF000000)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add to cart",
                            tint = Color.White
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun DetailScreen(modifier: Modifier = Modifier, navController: NavHostController, product: Product) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Text(text = "Detail Screen")
        Text(text = "${product.title}")
        OutlinedButton(onClick = { navController.navigate("homeScreen") }) {
            Text(text = "Go Back")
        }
    }
}

@Composable
fun RatingStars(rating: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        repeat(rating) {
            Icon(
                imageVector = Icons.Sharp.Star,
                contentDescription = "Filled star",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
