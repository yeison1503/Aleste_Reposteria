package com.example.alestereposteria.sever.repository

import com.example.alestereposteria.sever.Purchase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AlesteServerRepository {
    var db = Firebase.firestore

    fun savePurchase(product: String, dimension: String, cakefilling: String, msg: String, comments: String, publicationDate: String) {

        val documentPurchase = db.collection("Purchase").document()

        val purchase = Purchase(
            id = documentPurchase.id,
            product = product,
            dimension = dimension,
            cakefilling = cakefilling,
            msg = msg,
            comments = comments,
            purchase_date = publicationDate
        )
        db.collection("Purchase").document(documentPurchase.id).set(purchase)
    }


}