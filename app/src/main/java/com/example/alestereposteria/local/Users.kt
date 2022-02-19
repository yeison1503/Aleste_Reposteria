package com.example.alestereposteria.local

import java.io.Serializable

data class Users(
    val name: String,
    val cell_phone: Int,
    val address: String,
    val email: String,
    val password: String
) : Serializable
