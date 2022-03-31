package com.example.alestereposteria.sever.repository

import com.example.alestereposteria.sever.Purchase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class AlesteServerRepository {
    var db = Firebase.firestore
    var user = Firebase.auth.currentUser

    fun savePurchase(
        product: String,
        dimension: String,
        cakefilling: String,
        msg: String,
        comments: String,
        publicationDate: String,
        uid: String
    ) {

        val documentPurchase = db.collection("Purchase").document()

        val purchase = Purchase(
            id = documentPurchase.id,
            product = product,
            dimension = dimension,
            cakefilling = cakefilling,
            msg = msg,
            comments = comments,
            purchase_date = publicationDate,
            userid = uid
        )
        db.collection("Purchase").document(documentPurchase.id).set(purchase)

        user?.let {
            db.collection("users").document(it.uid).collection("mis_compras")
                .document(documentPurchase.id).set(purchase)
        }
    }

    suspend fun loadPurchase(): QuerySnapshot {
        return withContext(Dispatchers.IO) {
            db.collection("Purchase").get().await()
        }
    }


}