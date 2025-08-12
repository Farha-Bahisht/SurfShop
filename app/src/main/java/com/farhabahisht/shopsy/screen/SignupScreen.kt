package com.farhabahisht.shopsy.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.farhabahisht.shopsy.viewModel.AuthViewModel
import com.farhabahisht.shopsy.viewModel.model.AppUtil

@Composable
fun SignupScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel = viewModel() ){

    var email by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(text ="Let's Get Started",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 30.sp,
                //fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,

                )
        )
        Spacer(modifier = Modifier.height(15.dp))

        Text(text ="Create an account to continue ",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 22.sp,


                )
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(text = "Name")
            },
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
            email = it
        },
            label = {
                Text(text = "Email")
            },
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                authViewModel.signup(email,name,password){success,errorMessage->
                    if(success){
                        navController.navigate("home"){
                            popUpTo("auth") {inclusive = true}
                        }


                    }else {
                        AppUtil.showToast(context,errorMessage?:"Something went wrong ")
                    }

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF738A6E) ),
                    shape = RoundedCornerShape(8.dp)

        ) {
            Text(
                text = "Continue",
                fontSize = 22.sp

            )
        }



    }
}