package com.example.alestereposteria.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alestereposteria.databinding.ActivitySplashBinding
import com.example.alestereposteria.ui.login.LoginFragment
import java.util.*
import kotlin.concurrent.timerTask


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        //Thread.sleep(200)
        val timer = Timer()
        timer.schedule(timerTask {
            goToMainActivity()
        },  2000
        )
    }

    private fun goToMainActivity() {
        val intent = Intent(this, LoginFragment::class.java)
        startActivity(intent)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.flags =Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        finish()
        //Destreye completamente la actividad
    }
}