package com.example.alestereposteria.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class UsersDatabase:RoomDatabase() {

    abstract fun UsersDao(): UsersDao

}