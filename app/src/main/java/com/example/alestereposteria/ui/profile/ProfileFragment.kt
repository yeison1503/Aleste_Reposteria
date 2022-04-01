package com.example.alestereposteria.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alestereposteria.databinding.FragmentProfileBinding
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


        user?.let { profileViewModel.searchUser(it.uid) }


        profileBinding.signOutProfileButton.setOnClickListener {
            auth.signOut()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToMainActivity())
            activity?.finish()
        }

    }

    private fun onFindUserDoneSuscribe(user: User?) {
        with(profileBinding) {
            Picasso.get().load(user?.urlPicture).into(profileImageView)
            profileNameTextView.text = user?.name
            cellphoneProfileEditTextExample.text = user?.phone
            addressProfileEditExample.text = user?.email
        }
    }

}