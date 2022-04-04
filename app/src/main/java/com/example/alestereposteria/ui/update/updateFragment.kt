package com.example.alestereposteria.ui.update

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alestereposteria.databinding.FragmentUpdateBinding
import com.example.alestereposteria.sever.Purchase
import java.text.SimpleDateFormat
import java.util.*

class updateFragment : Fragment() {


    private lateinit var updateBinding: FragmentUpdateBinding
    private lateinit var updateViewModel: UpdateViewModel
    private var cal = Calendar.getInstance()
    private var publicationDate = ""
    private val REQUEST_IMAGE_CAPTURE = 1

    private var updatePurchase: Purchase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateBinding = FragmentUpdateBinding.inflate(inflater, container, false)
        updateViewModel = ViewModelProvider(this)[UpdateViewModel::class.java]
        return updateBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateViewModel.findPurchaseDone.observe(viewLifecycleOwner) { result ->
            onFindPurchaseDoneSuscribe(result)
        }
        updateViewModel.msgDone.observe(viewLifecycleOwner) { result ->
            onMsgDoneSuscribe(result)
        }

        updateViewModel.dataValidated.observe(viewLifecycleOwner) { result ->
            onDateValidateSuscribe(result)
        }

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_YEAR, dayOfMonth)

            val format = "dd-MM-yyyy"
            val simpleDateFormat = SimpleDateFormat(format, Locale.US)
            publicationDate = simpleDateFormat.format(cal.time).toString()
            updateBinding.dateOfOrdenButton.text = publicationDate
        }

        with(updateBinding) {
            searchButton.setOnClickListener {
                val product = when {
                    cupcakeCheckBox.isChecked -> "Cupcake"
                    cookiesCheckBox.isChecked -> "Galletas"
                    dessertCheckBox.isChecked -> "Postre"
                    else -> "Torta"
                }
                updateViewModel.searchProduct(
                    product,
                    sizeEditText.text.toString(),
                    cakeFillingEditText.text.toString()
                )
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

            photoImageView.setOnClickListener {
                dispatchTakePictureIntent()
            }

            saveButton.setOnClickListener {
                updateViewModel.validateFields(
                    sizeEditText.text.toString(),
                    cakeFillingEditText.text.toString(),
                    messageEditText.text.toString(),
                    dateOfOrdenButton.text.toString()
                )
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
            updateBinding.photoImageView.setImageBitmap(imageBitmap)
        }
    }

    private fun onFindPurchaseDoneSuscribe(purchase: Purchase?) {
        if (purchase == null) {
            Toast.makeText(requireContext(), "Pedido no encontrado", Toast.LENGTH_SHORT).show()
        } else {
            updatePurchase = purchase
            with(updateBinding) {
                messageTextInputLayout.isVisible = true
                //messageEditText.isVisible = true
                searchButton.isVisible = false
                saveButton.isVisible = true
                dateOfOrderTextView.isVisible = true
                dateOfOrdenButton.isVisible = true
                photoImageView.isVisible = true
                remarksTextInputLayout.isVisible = true

                sizeEditText.setText(updatePurchase?.dimension)
                cakeFillingEditText.setText(updatePurchase?.cakefilling)
                messageEditText.setText(updatePurchase?.msg)
                remarksEditText.setText(updatePurchase?.comments)
                dateOfOrdenButton.text = updatePurchase?.purchase_date
            }
        }
    }

    private fun onDateValidateSuscribe(result: Boolean?) {
        with(updateBinding) {

            val product = when {
                cupcakeCheckBox.isChecked -> "Cupcake"
                cookiesCheckBox.isChecked -> "Galletas"
                dessertCheckBox.isChecked -> "Postre"
                else -> "Torta"
            }

            val dimension = sizeEditText.text.toString()
            val cakefilling = cakeFillingEditText.text.toString()
            val msg = messageEditText.text.toString()
            val comments = remarksEditText.text.toString()


            updatePurchase?.id?.let {
                updateViewModel.savePurchaseServer(
                    it,
                    product,
                    dimension,
                    cakefilling,
                    msg,
                    comments,
                    publicationDate
                )
            }
            onMsgDoneSuscribe("Pedido guardado con Éxito")
            findNavController().navigate(updateFragmentDirections.actionUpdateFragmentToHomeFragment())
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