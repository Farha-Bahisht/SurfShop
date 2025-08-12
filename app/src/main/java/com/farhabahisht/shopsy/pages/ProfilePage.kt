package com.farhabahisht.shopsy.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farhabahisht.shopsy.R
import com.farhabahisht.shopsy.screen.GlobalNavigation
import com.farhabahisht.shopsy.viewModel.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

@Composable
fun ProfilePage(modifier: Modifier = Modifier) {

    val userModel = remember {
        mutableStateOf(UserModel())
    }
    LaunchedEffect (Unit) {
        Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)
            .get().addOnCompleteListener{
                if (it.isSuccessful) {
                    val result = it.result.toObject(UserModel::class.java)
                    if(result !=null)
                        userModel.value = result
                }
            }
    }




   Column (
       modifier = modifier.fillMaxSize().padding(16.dp)

   ){
   Text(text= "Your Profile", style = TextStyle(
       fontSize = 24.sp,
       fontWeight = FontWeight.Bold)
   )
       Image(
           painter = painterResource(id = R.drawable.profile),
           contentDescription = "Profile Image",
           modifier = Modifier.height(200.dp).fillMaxWidth()

       )

       Text(
           text = userModel.value.name,
           fontSize = 26.sp,
           fontWeight = FontWeight.Bold,
           modifier = Modifier.fillMaxWidth(),
           textAlign = TextAlign.Center
       )

       Spacer(modifier = Modifier.height(16.dp))
       Text (
           text = "Email:",
           fontSize = 18.sp,
           fontWeight = FontWeight.Medium,
           )


       Text (text = userModel.value.email)

       Spacer(modifier = Modifier.height(10.dp))
       Text (
           text = "Address:",
           fontSize = 18.sp,
           fontWeight = FontWeight.Medium,
       )


       Text (text = "123, MainStreet, Disney, FL 1111")

       Spacer(modifier = Modifier.height(10.dp))
       Text (
           text = "Phone:",
           fontSize = 18.sp,
           fontWeight = FontWeight.Medium,
       )


       Text (text = "(123) 456-7890")

     TextButton(onClick = {
         FirebaseAuth.getInstance().signOut()
         val navController = GlobalNavigation.navController
         navController.popBackStack()
         navController.navigate("auth")
     },
         modifier = Modifier
             .fillMaxWidth()
             .align(Alignment.CenterHorizontally)
         ){
         Text(text = "Sign out", fontSize = 18.sp)
     }
   }
}
