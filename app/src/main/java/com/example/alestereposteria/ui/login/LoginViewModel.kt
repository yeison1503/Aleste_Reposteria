package com.example.alestereposteria.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alestereposteria.local.Users
import com.example.alestereposteria.local.repository.UsersRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val usersRepository = UsersRepository()

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg


    private val findUser: MutableLiveData<Users?> = MutableLiveData()
    val findUserDone: LiveData<Users?> = findUser

    @DelicateCoroutinesApi
    fun searchUser(emailUser: String, password: String) {
        if (emailUser.isEmpty() || password.isEmpty()){
            msg.value = "Debe digitar los campos"
        } else {
            GlobalScope.launch(Dispatchers.IO) {
               findUser.postValue(usersRepository.searchUser(emailUser))
            }
        }
    }
}

