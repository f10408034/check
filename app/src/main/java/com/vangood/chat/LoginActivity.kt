package com.vangood.chat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.vangood.chat.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val TAG = ActivityLoginBinding::class.java.simpleName
    var remember = false
    val MainResultLuncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result->
            Log.d(TAG, "back from LoginActivity")
        }
    val SignUpResultLuncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result->
        Log.d(TAG, "go to SignupActivity")
    }

//    val pref = requireContext().getSharedPreferences("atm", Context.MODE_PRIVATE)

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pref = getSharedPreferences("check", Context.MODE_PRIVATE)
        val checked = pref.getBoolean("rem_account", false)
        binding.cbLoginRemember.isChecked = checked
        binding.cbLoginRemember.setOnCheckedChangeListener { compoundButton, checked ->
            remember = checked
            pref.edit().putBoolean("rem_account", remember).apply()
            if (!checked) {
                pref.edit().putString("account", "").apply()
            }
        }
        val prefAccount = pref.getString("account","")
        if (prefAccount != "")
            binding.edLoginAccount.setText(prefAccount)

        binding.bLoginLogin.setOnClickListener {
            val account = binding.edLoginAccount.text.toString()
            val password = binding.edLoginPassword.text.toString()
            if (account == "jack" && password == "1234"){
                Log.d(TAG, "Login success")
                if (remember) {
                    pref.edit()
                        .putString("account", account)
                        .apply()
                }
                MainResultLuncher.launch(Intent(this, MainActivity::class.java))
                Toast.makeText(this, "welcome $account", Toast.LENGTH_LONG).show()
            }else {
                AlertDialog.Builder(this)
                    .setTitle("Login")
                    .setMessage("Login Failed")
                    .setPositiveButton("ok", null)
                    .show()
            }
        }
        binding.bLoginSignup.setOnClickListener {

            SignUpResultLuncher.launch(Intent(this, SignUpActivity::class.java))
        }
    }

}