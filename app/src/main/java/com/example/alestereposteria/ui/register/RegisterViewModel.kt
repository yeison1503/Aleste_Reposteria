package com.example.alestereposteria.ui.register

import android.util.Log
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alestereposteria.repository.UsersRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegisterViewModel : ViewModel() {

    private val usersRepository = UsersRepository()
    private lateinit var auth: FirebaseAuth

    private val msg: MutableLiveData<String> = MutableLiveData()
    val msgDone: LiveData<String> = msg

    private val dataValidate: MutableLiveData<Boolean> = MutableLiveData()
    val dataValidated: LiveData<Boolean> = dataValidate

    val passwordRegex = Pattern.compile(
        "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +        //at least 1 lower case letter
                "(?=.*[A-Z])" +        //at least 1 upper case letter
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$"
    )

    val cellPhoneRegex = Pattern.compile(
        "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                ".{10,}" +               //at least 4 characters
                "$"
    )

    fun DateValidate(
        name: String,
        cell_phone: String,
        email: String,
        password: String,
        re_password: String
    ) {
        if (name.isEmpty() || cell_phone.isEmpty() || email.isEmpty()
            || password.isEmpty() || re_password.isEmpty()
        ) {
            msg.value = "Debe digitar todos los campos"
        } else if (name.length <= 10) {
            msg.value = "El nombre debe tener mas de 10 caracteres"
        } else if (!cellPhoneRegex.matcher(cell_phone).matches()) {
            msg.value = "El número telefonico debe tener 10 caracteres"
        } else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            msg.value = "Correo Electronico no valido"
        } else if(!passwordRegex.matcher(password).matches()){
            msg.value = "Contraseña no valida, ¡debe ser mas segura!"
        } else if(password != re_password){
            msg.value = "Las Contraseñas deben ser iguales!"
        } else{

            msg.value = "Gracias por registrarse!"
            dataValidate.value = true

            /*GlobalScope.launch(Dispatchers.IO) {
                usersRepository.saveUser(name, cell_phone, email, password)
            }*/
        }
    }

}