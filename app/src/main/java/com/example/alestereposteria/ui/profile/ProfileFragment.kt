package com.example.alestereposteria.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alestereposteria.R
import com.example.alestereposteria.databinding.FragmentProfileBinding
import com.example.alestereposteria.databinding.FragmentRegisterBinding
import com.example.alestereposteria.ui.register.RegisterViewModel

class ProfileFragment : Fragment() {


    private lateinit var profileBinding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        return profileBinding.root
    }

}