package com.example.alestereposteria.ui.main

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alestereposteria.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

    }

    //advertencia cuando se oprime el boton hacia atras (onBackPressed)
    override fun onBackPressed() {
        val mensaje: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        mensaje.setTitle("¿Desea Salir de la Aplicacion?")
        mensaje.setCancelable(false)
        mensaje.setPositiveButton("Aceptar",
            DialogInterface.OnClickListener { _, _ -> finish() })
        mensaje.setNegativeButton("Cancelar",
            DialogInterface.OnClickListener { _, _ -> })
        mensaje.show()
    }

    //Boton para cerrar sesión desde la barra menú
    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
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
