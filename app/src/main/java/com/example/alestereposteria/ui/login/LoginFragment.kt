package com.example.alestereposteria.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.alestereposteria.databinding.FragmentLoginBinding
import com.example.alestereposteria.local.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var auth: FirebaseAuth



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth = Firebase.auth

        loginViewModel.msgDone.observe(viewLifecycleOwner) { msg ->
            onMsgDoneSuscribe(msg)
        }

        loginViewModel.findUserDone.observe(viewLifecycleOwner) { user ->
            onFindUserDone(user)
        }

        //Para pasar de un fragmento a otro fragmento, tener en cuenta desde hacia donde se va a ir y a cual se va a ir.
        loginBinding.registerTextView.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }


        with(loginBinding) {
            singInButton.setOnClickListener {

                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty() ){

                    //Para recuperar la contraseña
                    //auth.sendPasswordResetEmail(email)
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener() { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "signInWithEmail:success")
                                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToBottonActivity())
                                activity?.finish()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "signInWithEmail:failure", task.exception)
                                onMsgDoneSuscribe("Usuario o contraseña incorrecto, o debe registrarse")
                            }
                        }
                }else{
                    onMsgDoneSuscribe("Debe de llenar los campos")
                }

                /*loginViewModel.searchUser(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()

                )*/
            }
        }
    }

    private fun onFindUserDone(user: Users?) {
        if (user == null) {
            onMsgDoneSuscribe(msg = "Debe crear un usuario!")
        }else if(user.email == loginBinding.emailEditText.text.toString() && user.password == loginBinding.passwordEditText.text.toString()){
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToBottonActivity())
            activity?.finish()
        }else{
            onMsgDoneSuscribe(msg = "Correo o Contraseña Incorrectas!")
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