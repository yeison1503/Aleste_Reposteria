package com.example.alestereposteria.sever.repository

import com.example.alestereposteria.sever.Address
import com.example.alestereposteria.sever.Purchase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class AlesteServerRepository {
    private var db = Firebase.firestore
    private var user = Firebase.auth.currentUser

    fun savePurchase(
        product: String,
        dimension: String,
        cakefilling: String,
        msg: String,
        comments: String,
        publicationDate: String,
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
            userid = user?.uid
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

    suspend fun loadUser(): QuerySnapshot {
        return withContext(Dispatchers.IO) {
            db.collection("users").get().await()
        }
    }

    fun saveAddress(
        country: String,
        city: String,
        district: String,
        street: String,
        numberStreet: String,
        complement: String,
    ) {
        val documentAddress = db.collection("Address").document()

        val address = Address(
            id = documentAddress.id,
            country = country,
            city = city,
            district = district,
            street = street,
            numberStreet = numberStreet,
            complement = complement,
            userid = user?.uid
        )
        db.collection("Address").document(documentAddress.id).set(address)

        user?.let {
            db.collection("users").document(it.uid).collection("mis_address")
                .document(documentAddress.id).set(address)
        }
    }

}