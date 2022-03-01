@file:Suppress("DEPRECATION")

package com.example.alestereposteria.ui.splash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.alestereposteria.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var splashBinding: FragmentSplashBinding
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        splashBinding = FragmentSplashBinding.inflate(inflater, container, false)
        splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        return splashBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Handler().postDelayed(Runnable {
            //fragmentManager?.beginTransaction()?.remove(this)?.commit()
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
        }, 1000)

        //splashBinding.imageView.setOnClickListener{goToLoginFragment()}

    }

    /*private fun onValidate(result: Boolean?) {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
    }*/

    /*private fun goToLoginFragment() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
    }*/

}