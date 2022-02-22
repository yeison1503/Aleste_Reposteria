package com.example.alestereposteria.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.alestereposteria.databinding.FragmentLoginBinding
import com.example.alestereposteria.local.Users


class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel


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
                loginViewModel.searchUser(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }
    }

    private fun onFindUserDone(user: Users?) {


        if (user == null) {
            onMsgDoneSuscribe(msg = "Debe crear un usuario!")
        }else if(user.email == loginBinding.emailEditText.text.toString() && user.password == loginBinding.passwordEditText.text.toString()){
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToBottonActivity())

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