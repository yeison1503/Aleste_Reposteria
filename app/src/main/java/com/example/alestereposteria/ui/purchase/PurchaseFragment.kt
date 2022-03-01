package com.example.alestereposteria.ui.purchase

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alestereposteria.R
import com.example.alestereposteria.databinding.FragmentPurchaseBinding

class PurchaseFragment : Fragment() {

    private lateinit var purchaseBinding: FragmentPurchaseBinding
    private lateinit var purchaseViewModel: PurchaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        purchaseBinding = FragmentPurchaseBinding.inflate(inflater, container, false)
        purchaseViewModel = ViewModelProvider(this)[PurchaseViewModel::class.java]
        return purchaseBinding.root
    }

}