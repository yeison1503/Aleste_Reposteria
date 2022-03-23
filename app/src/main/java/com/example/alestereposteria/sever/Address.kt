package com.example.alestereposteria.sever

import java.io.Serializable

data class Address(

    var id: String? = null,
    var country: String? = null,
    var city: String? = null,
    var district: Int? = null,
    var street: String? = null,
    var number_street: String? = null,
    var complement: String? = null,
): Serializable
