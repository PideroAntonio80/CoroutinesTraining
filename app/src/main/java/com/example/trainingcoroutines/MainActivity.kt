package com.example.trainingcoroutines

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.trainingcoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Con la función with(binding) de debajo (línea 23) me ahorro tener que escribir
        "binding.bSubmit", "binding.etUser" y "binding.etPassword" pudiendo prescindir
        de "binding." delante del identificador de las vistas*/
        with(binding) {
            bSubmit.setOnClickListener {
                lifecycleScope.launch {
                    val success = withContext(Dispatchers.IO) {
                        validateLogin(etUser.text.toString(), etPassword.text.toString())
                    }
                    toast(if (success) "Success" else "Failure")
                }
            }
        }
    }

    private fun validateLogin(user: String, password: String): Boolean {
        Thread.sleep(2000)
        return user.isNotEmpty() && password.isNotEmpty()
    }
}

private fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}