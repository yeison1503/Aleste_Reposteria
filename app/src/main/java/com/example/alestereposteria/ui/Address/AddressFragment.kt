package com.example.alestereposteria.ui.Address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alestereposteria.databinding.FragmentAddressBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AddressFragment : Fragment() {

    private lateinit var addressBinding: FragmentAddressBinding
    private lateinit var addressviewModel: AddressViewModel

    private var user = Firebase.auth.currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addressBinding = FragmentAddressBinding.inflate(inflater, container, false)
        addressviewModel = ViewModelProvider(this)[AddressViewModel::class.java]
        return addressBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addressviewModel.msgDone.observe(viewLifecycleOwner) { result ->
            onMsgDoneSuscribe(result)
        }

        addressviewModel.dataValidated.observe(viewLifecycleOwner) { result ->
            onDateValidateSuscribe(result)
        }


        with(addressBinding) {

            user?.let {
                // Name, email address, and profile photo Url
                nameUsuarioTextView.text = user!!.email
            }

            saveButton.setOnClickListener {
                addressviewModel.validateFields(
                    countryEditText.text.toString(),
                    cityEditText.text.toString(),
                    districtEditText.text.toString(),
                    streetEditText.text.toString(),
                    numberStreetEditText.text.toString()
                )
            }
        }
    }

    private fun onDateValidateSuscribe(result: Boolean?) {
        with(addressBinding) {
            val country = countryEditText.text.toString()
            val city = cityEditText.text.toString()
            val district = districtEditText.text.toString()
            val street = streetEditText.text.toString()
            val numberStreet = numberStreetEditText.text.toString()
            val complement = complementEditText.text.toString()

            addressviewModel.saveAddressServer(country, city, district, street, numberStreet, complement)
            onMsgDoneSuscribe("Pedido guardado con Éxito")
            findNavController().navigate(AddressFragmentDirections.actionAddressFragmentToHomeFragment())
        }
    }

    private fun onMsgDoneSuscribe(msg: String) {
        Toast.makeText(
            requireContext(),
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }

}