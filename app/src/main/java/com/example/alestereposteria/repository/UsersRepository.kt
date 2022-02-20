package com.example.alestereposteria.repository

import android.util.Log
import com.example.alestereposteria.alestereposteria
import com.example.alestereposteria.local.Users
import com.example.alestereposteria.local.UsersDao
import java.sql.Types.NULL

class UsersRepository {

    suspend fun saveUser(name: String, cellphone: Int, address: String, email: String, password: String) {
        val users = Users(
            id = NULL,
            name = name,
            cell_phone = NULL,
            address = address,
            email = email,
            password = password
        )

        val userDao : UsersDao = alestereposteria.database.UsersDao()
        userDao.saveUser(users)

    }

    suspend fun searchUser(emailUser: String): Users {
        val usersDao: UsersDao = alestereposteria.database.UsersDao()
        return usersDao.searchUser(emailUser)
    }


}