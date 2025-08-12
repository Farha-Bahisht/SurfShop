package com.farhabahisht.shopsy.viewModel

import androidx.lifecycle.ViewModel
import com.farhabahisht.shopsy.viewModel.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AuthViewModel : ViewModel() {

    private val auth = Firebase.auth

    private val firestore = Firebase.firestore

    fun login(email: String, password:String,onResult: (Boolean, String) -> Unit){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if
                    (it.isSuccessful){
                    onResult(true, "")
                }
                else{
                    it.exception?.localizedMessage?.let { it1 -> onResult(false, it1) }

                }
            }

    }
    fun signup(email: String, name: String, password: String, onResult: (Boolean, String) -> Unit){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    var userId = it.result?.user?.uid

                    val userModel = UserModel(name,email,userId!!)
                    firestore.collection("users").document(userId)
                        .set(userModel)
                        .addOnCompleteListener{ dbTask ->
                            if(dbTask.isSuccessful){
                                onResult(true, "")
                            } else {
                                dbTask.exception?.localizedMessage?.let { msg ->
                                    onResult(false, msg)
                                }
                            }
                        }

                }
                else{
                    it.exception?.localizedMessage?.let { it1 -> onResult(false, it1) }
                }
            }


    }
}