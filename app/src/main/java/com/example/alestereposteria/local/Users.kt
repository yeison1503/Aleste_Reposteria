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
    @ColumnInfo(name = "cell_phone") var cell_phone: String = "",
    @ColumnInfo(name = "email") var email: String = "",
    @ColumnInfo(name = "password") var password: String = ""
) : Serializable


/*
@Entity(tableName = "table_Purchase")
data class Purchase(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Int = NULL,
    @ColumnInfo(name = "product") var product: String = "",
    @ColumnInfo(name = "dimension") var dimension: String = "",
    @ColumnInfo(name = "msg") var msg: String = "",
    @ColumnInfo(name = "cost") var cost: String = "",
    @ColumnInfo(name = "purchase_date") var purchase_date: String = "",
    @ColumnInfo(name = "comments") var comments: String = ""
) : Serializable


@Entity(tableName = "table_Location")
data class Location(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Int = NULL,
    @ColumnInfo(name = "country") var country: String = "",
    @ColumnInfo(name = "city") var city: String ="",
    @ColumnInfo(name = "district") var district: String = "",
    @ColumnInfo(name = "street") var street: String = "",
    @ColumnInfo(name = "number_street") var number_street: String = "",
    @ColumnInfo(name = "complement") var complement: String = ""
) : Serializable*/
