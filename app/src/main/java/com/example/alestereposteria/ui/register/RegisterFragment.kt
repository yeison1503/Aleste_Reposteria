package com.example.alestereposteria.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alestereposteria.databinding.FragmentRegisterBinding
import com.example.alestereposteria.sever.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {

    private lateinit var registerBinding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        registerBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        return registerBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase Auth
        auth = Firebase.auth


        registerViewModel.msgDone.observe(viewLifecycleOwner) { msg ->
            onMsgDoneSuscribe(msg)
        }

        registerViewModel.dataValidated.observe(viewLifecycleOwner) { validate ->
            onDateValidateSuscribe(validate)
        }

        with(registerBinding) {

            singInButton.setOnClickListener {
                //activity?.onBackPressed()
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }

            registerButton.setOnClickListener {
                registerViewModel.DateValidate(
                    nameEditText.text.toString(),
                    cellPhoneEditText.text.toString(),
                    emailEditText.text.toString(),
                    passwordEditText.text.toString(),
                    repPasswordEditText.text.toString(),
                )
            }
        }

    }

    private fun onDateValidateSuscribe(validate: Boolean?) {
        //activity?.onBackPressed()

        auth.createUserWithEmailAndPassword(
            registerBinding.emailEditText.text.toString(),
            registerBinding.passwordEditText.text.toString()
        )
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Registro", "createUserWithEmail:success")
                    //val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    //startActivity(intent)

                    createUser(
                        auth.currentUser?.uid,
                        registerBinding.emailEditText.text.toString(),
                        registerBinding.nameEditText.text.toString(),
                        registerBinding.cellPhoneEditText.text.toString()
                    )

                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Register", "createUserWithEmail:failure", task.exception)
                    onMsgDoneSuscribe("Creación del usuario fallida")
                }

            }
    }

    private fun createUser(uid: String?, email: String, name: String, cellphone: String) {
        val db = Firebase.firestore
        val user = User(uid = uid, email = email, name = name, phone = cellphone)

        uid?.let { uid ->
            db.collection("users").document(uid).set(user)
                .addOnCompleteListener {
                    onMsgDoneSuscribe("Usuario creado exitosamente")
                }
        }
    }

    private fun onMsgDoneSuscribe(msg: String?) {
        Toast.makeText(
            requireContext(),
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }

}