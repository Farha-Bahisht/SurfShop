package com.farhabahisht.shopsy.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.farhabahisht.shopsy.components.BannerView
import com.farhabahisht.shopsy.components.CategoriesView
import com.farhabahisht.shopsy.components.HeaderView

@Composable
fun HomePage(modifier: Modifier = Modifier , navController: NavHostController) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
      HeaderView(modifier)
        Spacer(modifier = Modifier.height(1.dp))
        BannerView(modifier = Modifier.height(200.dp))
        Text(
            "Categories",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            )
        )
        CategoriesView()
    }


    }


