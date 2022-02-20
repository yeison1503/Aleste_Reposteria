package com.example.alestereposteria

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.alestereposteria.local.UsersDatabase

class alestereposteria: Application() {

    companion object{
        lateinit var database: UsersDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            UsersDatabase::class.java,
            "Users_db"
        ).build()
    }

}