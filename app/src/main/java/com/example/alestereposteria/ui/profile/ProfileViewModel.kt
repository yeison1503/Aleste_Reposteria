package com.example.alestereposteria.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alestereposteria.sever.User
import com.example.alestereposteria.sever.repository.AlesteServerRepository
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {


    private val alesteServerRepository = AlesteServerRepository()

    private val findUser: MutableLiveData<User?> = MutableLiveData()
    val findUserDone: LiveData<User?> = findUser

    @DelicateCoroutinesApi
    fun searchUser(userId: String) {
        GlobalScope.launch(Dispatchers.IO){
            val result = alesteServerRepository.loadUser()
            var isFoundUser = false
            for (document in result){
                val user: User = document.toObject()
                if (userId == user.uid){
                    findUser.postValue(user)
                    isFoundUser = true
                }
                if (!isFoundUser) findUser.postValue(null)
            }
        }
    }


}