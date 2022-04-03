package com.example.alestereposteria.ui.purchase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alestereposteria.sever.repository.AlesteServerRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PurchaseViewModel : ViewModel() {


    private val alesteServerRepository = AlesteServerRepository()
    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val dataValidate: MutableLiveData<Boolean> = MutableLiveData()
    val dataValidated: LiveData<Boolean> = dataValidate


    fun validateFields(size: String, cakeFilling: String, message: String, dateOfOrden: String) {

        if (size.isEmpty() || cakeFilling.isEmpty() || message.isEmpty() || dateOfOrden.isEmpty()) {
            msg.value =
                "Debe digitar los campos del Tamaño, Relleno, Mensaje y Fecha de Entrega"
        } else if (cakeFilling.length <= 3  || size.isEmpty()) {
            msg.value =
                "Debe digitar los campos"
        } else if (cakeFilling.length > 5  || size.length > 5 || dateOfOrden.length > 5){
            dataValidate.value = true
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun savePurchaseServer(
        product: String,
        dimension: String,
        cakefilling: String,
        msg: String,
        comments: String,
        publicationDate: String,
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            alesteServerRepository.savePurchase(
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