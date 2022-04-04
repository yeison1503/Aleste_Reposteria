package com.example.alestereposteria.ui.purchase

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.alestereposteria.R
import com.example.alestereposteria.databinding.FragmentPurchaseBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class PurchaseFragment : Fragment() {

    private lateinit var purchaseBinding: FragmentPurchaseBinding
    private lateinit var purchaseViewModel: PurchaseViewModel

    private var cal = Calendar.getInstance()
    private var publicationDate = ""
    private var user = Firebase.auth.currentUser

    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        purchaseBinding = FragmentPurchaseBinding.inflate(inflater, container, false)
        purchaseViewModel = ViewModelProvider(this)[PurchaseViewModel::class.java]
        return purchaseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        purchaseViewModel.msgDone.observe(viewLifecycleOwner) { result ->
            onMsgDoneSuscribe(result)
        }

        purchaseViewModel.dataValidated.observe(viewLifecycleOwner) { result ->
            onDateValidateSuscribe(result)
        }

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_YEAR, dayOfMonth)

            val format = "dd-MM-yyyy"
            val simpleDateFormat = SimpleDateFormat(format, Locale.US)
            publicationDate = simpleDateFormat.format(cal.time).toString()
            purchaseBinding.dateOfOrdenButton.text = publicationDate
        }

        with(purchaseBinding){


            user?.let {
                // Name, email address, and profile photo Url
                nameUsuarioTextView.text = user!!.email
            }

            dateOfOrdenButton.setOnClickListener {
                DatePickerDialog(
                    requireContext(),
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            saveButton.setOnClickListener {
                purchaseViewModel.validateFields(
                   sizeEditText.text.toString(),
                    cakeFillingEditText.text.toString(),
                    messageEditText.text.toString(),
                    dateOfOrdenButton.text.toString()
                )
            }

            photoImageView.setOnClickListener{
                dispatchTakePictureIntent()
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager).also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            purchaseBinding.photoImageView.setImageBitmap(imageBitmap)
        }
    }

    private fun onDateValidateSuscribe(result: Boolean?) {
        with(purchaseBinding){

            val product = when {
                cupcakeCheckBox.isChecked ->  "Cupcake"
                cookiesCheckBox.isChecked ->  "Galletas"
                dessertCheckBox.isChecked ->  "Postre"
                else -> "Torta"
            }

            val dimension = sizeEditText.text.toString()
            val cakefilling = cakeFillingEditText.text.toString()
            val msg = messageEditText.text.toString()
            val comments = remarksEditText.text.toString()


            purchaseViewModel.savePurchaseServer(product, dimension, cakefilling, msg, comments, publicationDate)
            onMsgDoneSuscribe("Pedido guardado con Éxito")
            findNavController().navigate(PurchaseFragmentDirections.actionPurchaseFragmentToAddressFragment())
            clearOnPantalla()
        }
    }

    private fun clearOnPantalla() {
        with(purchaseBinding){
            sizeEditText.text?.clear()
            cakeFillingEditText.text?.clear()
            messageEditText.text?.clear()
            remarksEditText.text?.clear()
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