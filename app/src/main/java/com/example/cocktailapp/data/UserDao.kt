package com.example.cocktailapp.data

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insertAll(userList : List<User>)

    @Insert
    fun insert(user : User)

    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE username = :username")
    fun getUserByName(username : String): User

    @Update
    fun updateUser(user : User): Int

    @Delete
    fun delete(user : User)
}