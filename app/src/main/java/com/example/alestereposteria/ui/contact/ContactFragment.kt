package com.example.alestereposteria.ui.contact

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alestereposteria.databinding.FragmentContactBinding

class ContactFragment : Fragment() {


    private lateinit var contactBinding: FragmentContactBinding
    private lateinit var contactviewModel: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contactBinding = FragmentContactBinding.inflate(inflater, container, false)
        contactviewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        return contactBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(contactBinding){

            facebookEditText.setOnClickListener{
                val facebook = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/552485732526560"))
                startActivity(facebook)
            }

            instagramEditText.setOnClickListener{
                val instagram = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/reposteriaaleste?utm_medium=copy_link"))
                startActivity(instagram)
            }

            whatsAppEditText.setOnClickListener{
                val whatsapp = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/573164347957"))
                startActivity(whatsapp)
            }

        }

    }


}