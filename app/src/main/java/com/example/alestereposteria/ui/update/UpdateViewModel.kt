package com.example.alestereposteria.ui.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alestereposteria.sever.Purchase
import com.example.alestereposteria.sever.repository.AlesteServerRepository
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateViewModel : ViewModel() {

    private val alesteServerRepository = AlesteServerRepository()

    private val findPurchase: MutableLiveData<Purchase?> = MutableLiveData()
    val findPurchaseDone: LiveData<Purchase?> = findPurchase

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val dataValidate: MutableLiveData<Boolean> = MutableLiveData()
    val dataValidated: LiveData<Boolean> = dataValidate

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

    fun validateFields(size: String, cakeFilling: String, message: String, dateOfOrden: String) {

        if (size.isEmpty() || cakeFilling.isEmpty() || message.isEmpty() || dateOfOrden.isEmpty()) {
            msg.value =
                "Debe digitar los campos del Tamaño, Relleno, Mensaje y Fecha de Entrega"
        } else if (cakeFilling.length <= 3 || size.isEmpty()) {
            msg.value =
                "Debe digitar los campos"
        } else if (cakeFilling.length > 5 || size.length > 5 || dateOfOrden.length > 5) {
            dataValidate.value = true
        }
    }

    fun savePurchaseServer(
        id: String,
        product: String,
        dimension: String,
        cakefilling: String,
        msg: String,
        comments: String,
        publicationDate: String,
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            alesteServerRepository.updatePurchase(
                id,
                product,
                dimension,
                cakefilling,
                msg,
                comments,
                publicationDate,
            )
        }
    }
}
