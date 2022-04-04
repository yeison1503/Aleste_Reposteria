package com.example.alestereposteria.ui.order_status

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.alestereposteria.R
import com.example.alestereposteria.databinding.FragmentOrderStatusBinding
import com.example.alestereposteria.databinding.FragmentSplashBinding
import com.example.alestereposteria.ui.splash.SplashViewModel

class OrderStatusFragment : Fragment() {

    private lateinit var orderStatusBinding: FragmentOrderStatusBinding
    private lateinit var orderStatusViewModel: OrderStatusViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        orderStatusBinding = FragmentOrderStatusBinding.inflate(inflater, container, false)
        orderStatusViewModel = ViewModelProvider(this)[OrderStatusViewModel::class.java]
        return orderStatusBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(orderStatusBinding){

            updateButton.setOnClickListener{
                findNavController().navigate(OrderStatusFragmentDirections.actionOrderStatusFragmentToUpdateFragment())
            }

            deleteButton.setOnClickListener{
                findNavController().navigate(OrderStatusFragmentDirections.actionOrderStatusFragmentToDeleteFragment())
            }

            contactButton.setOnClickListener{
                findNavController().navigate(OrderStatusFragmentDirections.actionOrderStatusFragmentToContactFragment())
            }
        }

    }
}