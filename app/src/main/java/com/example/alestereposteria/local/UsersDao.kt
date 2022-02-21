package com.example.alestereposteria.local

import androidx.room.*

@Dao
interface UsersDao {

    @Insert
    suspend fun saveUser(users: Users)

    @Query("SELECT * FROM TABLE_USERS WHERE email LIKE :emailUser")
    suspend fun searchUser(emailUser: String): Users

}
