package com.example.alestereposteria.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.alestereposteria.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var registerBinding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

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

        registerViewModel.msgDone.observe(viewLifecycleOwner) { msg ->
            onMsgDoneSuscribe(msg)
        }

        registerViewModel.dataValidated.observe(viewLifecycleOwner){ validate ->
            onDateValidateSuscribe(validate)
        }

        with(registerBinding){

            registerButton.setOnClickListener{
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
       findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }

    private fun onMsgDoneSuscribe(msg: String?) {
        Toast.makeText(
            requireContext(),
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }

}