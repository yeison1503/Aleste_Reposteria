package com.example.alestereposteria.sever
import java.io.Serializable

data class User (
    var uid: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var name: String? = null,
    var urlPicture: String? = null
): Serializable