package com.hnpper.cocktailapp.data.network

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hnpper.cocktailapp.model.Cocktail
import com.hnpper.cocktailapp.model.User
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.tasks.await
import java.util.HashMap

@Singleton
class NetworkDataSource @Inject constructor() {

    companion object {
        private const val TAG = "Firebase"
    }

    private val auth: FirebaseAuth
    private val database: FirebaseFirestore

    var firebaseUser: FirebaseUser?
    lateinit var user: User
    var cocktails: List<Cocktail> = listOf()
    var users: List<User> = listOf()

    init {
        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        firebaseUser = auth.currentUser
    }

    fun logout(): Boolean {
        auth.signOut()
        return true
    }

    suspend fun initDatabaseForCurrent() {
        val id = firebaseUser!!.uid
        database.collection("users").document(id).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val data = document.data!!
                    user = createUser(data)
                }
            }
            .await()

        database.collection("users").document(id)
            .addSnapshotListener { document, exception ->
                if (exception != null) {
                    Log.w(TAG, exception)
                    return@addSnapshotListener
                }

                if (document != null && document.exists()) {
                    val data = document.data!!
                    user = createUser(data)
                } else {
                    Log.d(TAG, "Current data: null")
                }
            }


            val query = database.collection("users")

            query.get()
                .addOnSuccessListener { documents ->
                    if (documents != null) {
                        val users: MutableList<User> = mutableListOf()
                        for (document in documents) {
                            val data = document.data
                            users.add(createUser(data))
                        }
                        this.users = users
                    }
                }
                .await()

            query.addSnapshotListener { documents, exception ->
                if (exception != null) {
                    Log.w(TAG, "Listen failed.")
                    return@addSnapshotListener
                }

                if (documents != null) {
                    val users: MutableList<User> = mutableListOf()
                    for (document in documents) {
                        users.add(createUser(document.data))
                    }
                    this.users = users
                }
            }

    }



    suspend fun registerWithEmail(email: String, password: String): FirebaseUser? {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "registerInWithEmail:success")
                    firebaseUser = auth.currentUser
                }
            }.await()
        return firebaseUser
    }


    suspend fun signInWithEmail(email: String, password: String): Boolean {
        var authState = false
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                Log.d(TAG, "signInWithEmail:success")
                firebaseUser = auth.currentUser
                authState = true
            }
            .addOnFailureListener { e ->
                Log.w(TAG, e)
                authState = false
            }
            .await()

        return authState
    }

    fun changeEmail(email: String) {
        auth.currentUser!!.updateEmail(email)
    }

    fun changePassword(password: String) {
        auth.currentUser!!.updatePassword(password)
    }

    suspend fun getUser(id: String): User {
        var user = User("","","","","",listOf())
        database.collection("users").document(id).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val data = document.data!!
                    user = createUser(data)
                }
            }
            .await()
        return user
    }

    private fun createUser(data: Map<String, Any?>): User {
        var user: User? = null
        val id = data["id"] as String
        val name = data["name"] as String
        val password = data["password"] as String
        val email = data["email"] as String
        val photoImageUrl = data["photoImageUrl"] as String?
        val favList = data["favList"] as String //convert to list of integers

        user = User(id, name, password, email, photoImageUrl, favList)

        return user!!
    }
}