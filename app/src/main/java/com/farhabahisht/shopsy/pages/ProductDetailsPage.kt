package com.farhabahisht.shopsy.pages

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.farhabahisht.shopsy.viewModel.model.AppUtil
import com.farhabahisht.shopsy.viewModel.model.ProductModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType


val Sage = Color(0xFF738A6E)
@Composable
fun ProductDetailsPage(modifier: Modifier,productId: String){


    var product by remember {
        mutableStateOf(ProductModel())

    }

    var context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        Firebase.firestore.collection("data")
            .document("stock")
            .collection("products").document(productId).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val result = it.result.toObject(ProductModel::class.java)
                    if (result != null) {
                        product = result

                    }

                }
            }
    }

    Column(modifier = modifier.
    fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState() )
    ){
        Text(
            text = product.title,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Column{
            val pagerState = rememberPagerState(0) {
                product.imageUrl.size
            }

            HorizontalPager(
                state = pagerState,
                pageSpacing = 23.dp
            ) {
                AsyncImage(
                    model = product.imageUrl.get(it),
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .height(220.dp)
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            DotsIndicator(
                dotCount = product.imageUrl.size,
                type = ShiftIndicatorType(
                    DotGraphic(
                        size = 6.dp,
                        color = Sage
                    )
                ),
                pagerState = pagerState

            )




        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "$${product.price}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.weight(3f))
            IconButton(onClick = {
                // Add-to-cart logic (if needed)
            }) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Add to favorite"
                )
            }
        }
        Button(onClick = {AppUtil.addItemToCart(context,productId)},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Sage,
                contentColor = Color.White
            ),


            )
        {
            Text(text = "Add to cart", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = " Product Description : ",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = product.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp))


    }

}