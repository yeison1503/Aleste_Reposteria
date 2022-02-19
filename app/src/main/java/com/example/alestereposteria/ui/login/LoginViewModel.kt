package com.example.alestereposteria.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    /*private val dataValidate: MutableLiveData<Boolean> = MutableLiveData()
    val dataValidated: LiveData<Boolean> = dataValidate*/

    fun searchUser(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()){

            msg.value = "Debe digitar nombre, autor y número de páginas"

        } else {
            msg.value = "Logeado"
            //dataValidate.value = true
        }
    }
}