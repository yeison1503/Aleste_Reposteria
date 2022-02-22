package com.example.alestereposteria.ui.main

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.alestereposteria.R
import com.example.alestereposteria.databinding.ActivityMainBinding
import com.example.alestereposteria.ui.bottom.BottonActivity
import com.example.alestereposteria.ui.home.HomeFragment
import com.example.alestereposteria.ui.login.LoginFragment
import com.example.alestereposteria.ui.login.LoginFragmentDirections
import com.example.alestereposteria.ui.splash.SplashFragment
import com.example.alestereposteria.ui.splash.SplashFragmentDirections
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding
    //private var cal = Calendar.getInstance()
    //private var publicationDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)



    }

    // Boton para cerrar sesión desde la barra menú
    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_sing_out -> goToLoginActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLoginActivity(){
        val intent = Intent(this, LoginFragment::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }*/
}