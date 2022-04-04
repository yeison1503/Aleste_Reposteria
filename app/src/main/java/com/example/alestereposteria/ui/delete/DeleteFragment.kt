package com.example.alestereposteria.ui.delete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alestereposteria.R
import com.example.alestereposteria.databinding.FragmentDeleteBinding
import com.example.alestereposteria.sever.Purchase
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DeleteFragment : Fragment() {

    private lateinit var deleteBinding: FragmentDeleteBinding
    private lateinit var deleteviewModel: DeleteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deleteBinding = FragmentDeleteBinding.inflate(inflater, container, false)
        deleteviewModel = ViewModelProvider(this)[DeleteViewModel::class.java]
        return deleteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deleteviewModel.findPurchaseDone.observe(viewLifecycleOwner) { result ->
            onFindPurchaseDoneSuscribe(result)
        }



        with(deleteBinding) {
            searchButton.setOnClickListener {
                val product = when {
                    cupcakeCheckBox.isChecked -> "Cupcake"
                    cookiesCheckBox.isChecked -> "Galletas"
                    dessertCheckBox.isChecked -> "Postre"
                    else -> "Torta"
                }
                deleteviewModel.searchProduct(
                    product,
                    sizeEditText.text.toString(),
                    cakeFillingEditText.text.toString()
                )
            }
        }


    }

    private fun onFindPurchaseDoneSuscribe(purchase: Purchase?) {
        if (purchase == null) {
            Toast.makeText(requireContext(), "Pedido no encontrado", Toast.LENGTH_SHORT).show()
        } else {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.warning_title))
                .setMessage(resources.getString(R.string.delete_purchase_msg, purchase.product))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                    deleteviewModel.deleteProduct(purchase)
                    deleteBinding.sizeEditText.text?.clear()
                    deleteBinding.cakeFillingEditText.text?.clear()
                }
                .show()
            findNavController().navigate(DeleteFragmentDirections.actionDeleteFragmentToHomeFragment())
        }
    }
}
