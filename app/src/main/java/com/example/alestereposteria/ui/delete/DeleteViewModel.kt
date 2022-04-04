package com.example.alestereposteria.ui.delete

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alestereposteria.sever.Purchase
import com.example.alestereposteria.sever.repository.AlesteServerRepository
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DeleteViewModel : ViewModel() {

    private val alesteServerRepository = AlesteServerRepository()

    private val findPurchase: MutableLiveData<Purchase?> = MutableLiveData()
    val findPurchaseDone: LiveData<Purchase?> = findPurchase

    fun searchProduct(product: String, size: String, cakefilling: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val result = alesteServerRepository.loadProduct()

            var isFoundAddress = false
            if (result != null) {
                for (document in result) {
                    val purchase: Purchase = document.toObject()
                    if (product == purchase.product && size == purchase.dimension && cakefilling == purchase.cakefilling) {
                        findPurchase.postValue(purchase)
                        isFoundAddress = true
                    }
                }
            }
            if (!isFoundAddress) findPurchase.postValue(null)
        }
    }

    fun deleteProduct(purchase: Purchase) {
        GlobalScope.launch(Dispatchers.IO) {
            alesteServerRepository.deleteBook(purchase)
        }
    }
}