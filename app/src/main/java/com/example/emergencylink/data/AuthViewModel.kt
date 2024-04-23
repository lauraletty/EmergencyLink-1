package com.example.firebasestorage.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.emergencylink.navigation.HOME_URL
import com.example.emergencylink.navigation.LOGIN_URL
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(var navController:NavHostController, var context:Context) {
    var mAuth:FirebaseAuth
    init {
        mAuth = FirebaseAuth.getInstance()
    }
    fun signup(email:String, password:String){
        Toast.makeText(context, "Clicked $email", Toast.LENGTH_SHORT).show()
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(context, "Register successful", Toast.LENGTH_SHORT).show()
                navController.navigate(HOME_URL)
            }else{
                Toast.makeText(context, "${it.exception!!.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun login(email:String, password:String){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                navController.navigate(HOME_URL)
            }
        }
    }

    fun logout(){
        mAuth.signOut()
        navController.navigate(LOGIN_URL)
    }

    fun isLoggedIn():Boolean{
        return mAuth.currentUser != null
    }
}