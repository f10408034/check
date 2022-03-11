package com.vangood.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.vangood.chat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    lateinit var binding: ActivityMainBinding
    //
    val personResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
        Log.d(TAG, "back from LoginActivity with data? ")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //botton funtion
        setupFunctions()
    }

    private fun setupFunctions() {
        binding.bPerson.setOnClickListener {
            personResultLauncher.launch(Intent(this, LoginActivity::class.java))
        }
        binding.bSearch.setOnClickListener {

        }
        binding.bHome.setOnClickListener {

        }
    }
}