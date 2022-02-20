package com.example.alestereposteria.ui.main

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alestereposteria.R
import com.example.alestereposteria.databinding.ActivityMainBinding
import com.example.alestereposteria.ui.login.LoginFragment
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding
    //private var cal = Calendar.getInstance()
    //private var publicationDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        /*val dateSetListener = DatePickerDialog.OnDateSetListener{ _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_YEAR, dayOfMonth)

            val format = "dd-MM-yyyy"
            val simpleDateFormat = SimpleDateFormat(format, Locale.US)
            publicationDate = simpleDateFormat.format(cal.time).toString()
            mainBinding.dateOfOrdenButton.text = publicationDate
        }

        with(mainBinding) {

            dateOfOrdenButton.setOnClickListener{
                DatePickerDialog(
                    this@MainActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            saveButton.setOnClickListener {
                if (nameUserEditText.text?.isEmpty() == true || phoneNumberEditText.text?.isEmpty() == true
                    //remarksEditText.text?.isEmpty() == true
                    ) {
                    Toast.makeText(
                        applicationContext,
                        "Debe digitar nombre y número telefonico",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val nameUser = nameUserEditText.text.toString()
                    val cellphone = phoneNumberEditText.text.toString()
                    val remarks = remarksEditText.text.toString()

                    var product = ""
                    if (cakeCheckBox.isChecked) product = "Torta"
                    if (cupcakeCheckBox.isChecked) product += "Cupcake"
                    if (cookiesCheckBox.isChecked) product += "Galletas"
                    if (dessertCheckBox.isChecked) product += "Postre"


                    infoTextView.text =
                        getString(
                            R.string.info,
                            nameUser,
                            cellphone,
                            remarks,
                            product,
                            publicationDate)
                }
            }
        }*/
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