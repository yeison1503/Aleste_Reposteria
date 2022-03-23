package com.example.alestereposteria.sever

import java.io.Serializable

data class Purchase(

    var id: String? = null,
    var product: String? = null,
    var dimension: String? = null,
    var msg: Int? = null,
    var cost: String? = null,
    var purchase_date: String? = null,
    var comments: String? = null,
): Serializable