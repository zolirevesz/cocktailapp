package com.hnpper.cocktailapp.interactors

import android.net.Uri
import com.hnpper.cocktailapp.data.network.NetworkDataSource
import com.hnpper.cocktailapp.model.User
import javax.inject.Inject

class FirebaseInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource
) {
    suspend fun registerWithEmail(
        user: User,
        password: String,
        photoUri: Uri?
    ): Boolean {
        var registerState = false
        var uploadPhotoState = true
        val firebaseUser = networkDataSource.registerWithEmail(user.email, password)
        if (firebaseUser != null) {
            user.id = firebaseUser.uid

            if (uploadPhotoState) {
                registerState = networkDataSource.saveUser(user)

            }
        }
        if (!registerState) {
            firebaseUser!!.delete()
        }
        if (registerState) {
            networkDataSource.initDatabaseForCurrent()
        }
        return registerState
    }

    suspend fun existActiveUser(): Boolean {
        if (networkDataSource.firebaseUser != null) {
            networkDataSource.initDatabaseForCurrent()
            return true
        }
        return false
    }

    suspend fun signInWithEmail(email: String, password: String): Boolean {
        if (networkDataSource.signInWithEmail(email, password)) {
            networkDataSource.initDatabaseForCurrent()
            return true
        }
        return false
    }

    fun getUser(): User {
        return networkDataSource.user
    }

    suspend fun getUser(id: String): User {
        return networkDataSource.getUser(id)
    }


    suspend fun saveUser(user: User, password: String?, photoUri: Uri?): Boolean {
        if (password != null) {
            networkDataSource.changeEmail(user.email)
            networkDataSource.changePassword(password)
        } else {
            networkDataSource.changeEmail(user.email)
        }
        networkDataSource.saveUser(user)

        return true
    }

    fun logout(): Boolean {
        return networkDataSource.logout()
    }
}