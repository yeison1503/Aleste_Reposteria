package com.example.alestereposteria.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Types.NULL

@Entity(tableName = "table_Users")
data class Users(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Int = NULL,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "cell_phone") var cell_phone: Int = 0,
    @ColumnInfo(name = "address") var address: String = "",
    @ColumnInfo(name = "email") var email: String = "",
    @ColumnInfo(name = "password") var password: String = ""
) : Serializable
