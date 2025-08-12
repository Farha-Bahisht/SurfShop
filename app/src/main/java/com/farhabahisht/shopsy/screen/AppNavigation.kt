package com.farhabahisht.shopsy.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.farhabahisht.shopsy.pages.CategoryProductsPage
import com.farhabahisht.shopsy.pages.CheckoutPage
import com.farhabahisht.shopsy.pages.ProductDetailsPage
import com.farhabahisht.shopsy.screen.GlobalNavigation.navController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun AppNavigation(modifier: Modifier = Modifier) {


    val navController = rememberNavController()
    GlobalNavigation.navController = navController

    val isLoggedIn = FirebaseAuth.getInstance().currentUser!= null
    val firstPage = if (isLoggedIn) "home" else "auth"

    NavHost(
        navController = navController, startDestination =  firstPage) {


        composable("auth") {
            AuthScreen(modifier = modifier, navController = navController)
        }

        composable("login") {
            LoginScreen(modifier = modifier, navController = navController)
        }

        composable("signup") {
            SignupScreen(modifier = modifier, navController = navController)
        }

        composable("home") {
            HomeScreen(modifier = modifier, navController = navController)
        }
        composable("category-products/{categoryId}") {
            var categoryId = it.arguments?.getString("categoryId")
            CategoryProductsPage(modifier, categoryId ?: "")
        }

        composable("product-details/{productId}") {
            var productId = it.arguments?.getString("productId")
            ProductDetailsPage(modifier, productId ?: "")
        }

        composable("checkout") {
            CheckoutPage(modifier = modifier)
        }



    }
}

object GlobalNavigation {
    lateinit var navController: NavHostController
}

