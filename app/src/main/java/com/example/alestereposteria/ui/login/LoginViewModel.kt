package com.example.alestereposteria.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alestereposteria.local.Users
import com.example.alestereposteria.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val usersRepository = UsersRepository()

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg


    private val findUser: MutableLiveData<Users> = MutableLiveData()
    val findUserDone: LiveData<Users> = findUser

    fun searchUser(emailUser: String, password: String) {
        if (emailUser.isEmpty() || password.isEmpty()){
            msg.value = "Debe digitar los campos"
        } else {

            GlobalScope.launch(Dispatchers.IO) {
               findUser.postValue(usersRepository.searchUser(emailUser))
            }
           /* if (User.password == password){
                dataValidate.value = true
            }else {
                msg.value = "Usuario o Contraseña incorrectos!"
            }*/
        }
    }
}