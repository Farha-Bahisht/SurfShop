package com.farhabahisht.shopsy.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.farhabahisht.shopsy.screen.GlobalNavigation
import com.farhabahisht.shopsy.viewModel.model.AppUtil
import com.farhabahisht.shopsy.viewModel.model.ProductModel

@Composable
fun ProductItemView(
    modifier: Modifier = Modifier,
    product: ProductModel
)
{
    val context = LocalContext.current

    Column(

        modifier = modifier
            .clickable {
                GlobalNavigation.navController.navigate("product-details/${product.id}")
            }
    ) {
        AsyncImage(
            model = product.imageUrl.firstOrNull(),
            contentDescription = product.title,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
        Text(
            text = product.title,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "$${product.price}")
            Spacer(modifier = Modifier.weight(2f))
            IconButton(onClick = {
                AppUtil.addItemToCart(context, product.id)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add to cart"
                )
            }
        }
    }
}
