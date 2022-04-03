package com.example.alestereposteria.sever

import java.io.Serializable

data class Address(

    var id: String? = null,
    var country: String? = null,
    var city: String? = null,
    var district: String? = null,
    var street: String? = null,
    var numberStreet: String? = null,
    var complement: String? = null,
    var userid: String? = null
): Serializable
