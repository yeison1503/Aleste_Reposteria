package com.example.alestereposteria.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alestereposteria.databinding.FragmentProfileBinding
import com.example.alestereposteria.sever.Address
import com.example.alestereposteria.sever.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {


    private lateinit var profileBinding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var auth: FirebaseAuth
    private var user = Firebase.auth.currentUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        profileViewModel.findUserDone.observe(viewLifecycleOwner) { result ->
            onFindUserDoneSuscribe(result)
        }

        profileViewModel.findAddressDone.observe(viewLifecycleOwner) { result ->
            onFindAddressDoneSuscribe(result)
        }

        user?.let { profileViewModel.searchUser(it.uid) }

        user?.let { profileViewModel.searchAddress(it.uid) }

        profileBinding.signOutProfileButton.setOnClickListener {
            auth.signOut()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToMainActivity())
            activity?.finish()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun onFindAddressDoneSuscribe(address: Address?) {

        profileBinding.addressProfileEditExample.text =
            address?.city + "-" + address?.country + "\n" + "Barrio: " + address?.district + "\n" + "Direccion: " + address?.street + " #" + address?.numberStreet
    }

    private fun onFindUserDoneSuscribe(user: User?) {
        with(profileBinding) {
            Picasso.get().load(user?.urlPicture).into(profileImageView)
            profileNameTextView.text = user?.name
            cellphoneProfileEditTextExample.text = user?.phone
            emailProfileEditExample.text = user?.email
        }
    }

}