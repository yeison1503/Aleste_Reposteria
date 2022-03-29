package com.example.alestereposteria.sever

import java.io.Serializable

data class Purchase(

    var id: String? = null,
    var product: String? = null,
    var dimension: String? = null,
    var cakefilling: String? = null,
    var msg: String? = null,
    var purchase_date: String? = null,
    var comments: String? = null,
    var urlPicture: String? = null
): Serializable