package com.example.alestereposteria.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alestereposteria.sever.Purchase
import com.example.alestereposteria.sever.repository.AlesteServerRepository
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val alesteServerRepository = AlesteServerRepository()

    private var purchaseList: ArrayList<Purchase> = ArrayList()

    private val loadPurchaseFromServer: MutableLiveData<ArrayList<Purchase>> = MutableLiveData()
    val loadPurchaseFromServerDone: LiveData<ArrayList<Purchase>> = loadPurchaseFromServer

    fun loadPurchaseFromServer() {
        GlobalScope.launch(Dispatchers.IO) {
            val querySnapshot = alesteServerRepository.loadPurchase()
            for (document in querySnapshot) {
                val purchaseServer: Purchase = document.toObject<Purchase>()
                purchaseList.add(purchaseServer)
            }
            loadPurchaseFromServer.postValue(purchaseList)
        }
    }

}