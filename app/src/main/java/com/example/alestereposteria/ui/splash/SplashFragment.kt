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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashFragment : Fragment() {

    private lateinit var splashBinding: FragmentSplashBinding
    private lateinit var splashViewModel: SplashViewModel
    private lateinit var auth: FirebaseAuth

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

        auth = Firebase.auth

        Handler().postDelayed(Runnable {
            //fragmentManager?.beginTransaction()?.remove(this)?.commit()
            if (auth.currentUser == null){
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }else{
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToBottonActivity())
                activity?.finish()
            }

        }, 1000)


    }

    /*private fun onValidate(result: Boolean?) {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
    }*/

    /*private fun goToLoginFragment() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
    }*/

}