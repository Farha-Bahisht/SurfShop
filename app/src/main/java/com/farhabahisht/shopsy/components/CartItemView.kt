package com.farhabahisht.shopsy.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.farhabahisht.shopsy.screen.GlobalNavigation
import com.farhabahisht.shopsy.viewModel.model.AppUtil
import com.farhabahisht.shopsy.viewModel.model.ProductModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore



@Composable
fun CartItemView(modifier: Modifier = Modifier, productId: String, qty: Long) {
    var product by remember { mutableStateOf(ProductModel()) }
    var quantity = qty

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        Firebase.firestore.collection("data")
            .document("stock")
            .collection("products")
            .document(productId)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val result = it.result.toObject(ProductModel::class.java)
                    if (result != null) {
                        product = result
                    }
                }
            }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                GlobalNavigation.navController.navigate("product-details/${product.id}")
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            AsyncImage(
                model = product.imageUrl.firstOrNull(),
                contentDescription = product.title,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Info & controls
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Quantity controls
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        AppUtil.removeFromCart(context, product.id)
                    }) {
                        Text(text = "–", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }

                    Text(
                        text = quantity.toString(),
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.Medium
                    )

                    IconButton(onClick = {
                        AppUtil.addItemToCart(context, product.id)
                    }) {
                        Text(text = "+", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "$${product.price}",
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Delete button
            IconButton(onClick = {
                AppUtil.removeFromCart(context, product.id, removeAll = true)
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete item"
                )
            }
        }
    }
}
