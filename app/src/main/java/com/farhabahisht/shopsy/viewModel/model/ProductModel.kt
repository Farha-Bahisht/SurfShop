package com.farhabahisht.shopsy.viewModel.model

data class ProductModel(
    val id: String = "",
    val category: String = "",
    val title: String = "",
    val price: String = "",
    val description: String = "",
    val imageUrl: List<String> = emptyList()
)
