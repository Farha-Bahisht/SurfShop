package com.farhabahisht.shopsy.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic


@Composable
fun BannerView(modifier: Modifier = Modifier) {
    var bannerList by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(Unit) {
        Firebase.firestore.collection("data")
            .document("banners")
            .get()
            .addOnCompleteListener {
                bannerList = it.result.get("url") as List<String>
            }
    }

    Column(modifier = modifier) {
        val pagerState = rememberPagerState(0) {
            bannerList.size
        }

        HorizontalPager(
            state = pagerState,
            pageSpacing = 23.dp
        ) {
            AsyncImage(
                model = bannerList.get(it),
                contentDescription = "Banner",
                modifier = Modifier.fillMaxWidth()
            )
        }


    }
}
