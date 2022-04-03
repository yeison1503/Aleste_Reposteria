package com.example.alestereposteria.ui.Address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alestereposteria.sever.repository.AlesteServerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddressViewModel : ViewModel() {

    private val alesteServerRepository = AlesteServerRepository()

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val dataValidate: MutableLiveData<Boolean> = MutableLiveData()
    val dataValidated: LiveData<Boolean> = dataValidate

    fun validateFields(
        country: String,
        city: String,
        district: String,
        street: String,
        numberStreet: String
    ) {
        if (country.isEmpty() || city.isEmpty() || district.isEmpty() || street.isEmpty() || numberStreet.isEmpty()) {
            msg.value =
                "Debe digitar todos los campos"
        } else {
            dataValidate.value = true
        }
    }

    fun saveAddressServer(
        country: String,
        city: String,
        district: String,
        street: String,
        numberStreet: String,
        complement: String,
    ) {
        GlobalScope.launch(Dispatchers.IO) {
            alesteServerRepository.saveAddress(
                country,
                city,
                district,
                street,
                numberStreet,
                complement,
            )
        }
    }
}