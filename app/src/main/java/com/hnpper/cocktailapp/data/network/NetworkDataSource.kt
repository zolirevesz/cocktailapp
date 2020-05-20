package com.hnpper.cocktailapp.data.network

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.User

class NetworkDataSource {
    // Access a Cloud Firestore instance from your Activity
    val db = Firebase.firestore

    fun getUsers(): List<User> {
        db.collection("users").document("userDoc").get()
    }

    fun addUser(user: User) {
        db.collection("users").document("userDoc").set(user)
    }

    fun getCocktails(): List<Cocktail> {
        db.collection("users").document("cocktailDoc").get()
    }

    fun getCocktails(cocktail: Cocktail) {
        db.collection("users").document("cocktailDoc").set(cocktail)
    }
}