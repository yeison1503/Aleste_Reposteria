package com.example.alestereposteria.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    fun DateValidate(
        name: String,
        cell_phone: Int,
        address: String,
        email: String,
        password: String,
        re_password: String
    ) {
        if (name.isEmpty() || address.isEmpty() || email.isEmpty() || password.isEmpty() || re_password.isEmpty()) {
            msg.value = "Debe digitar todos los campos"
        } else {
            msg.value = "Campos correctos"

        }

    }
    // TODO: Implement the ViewModel
}