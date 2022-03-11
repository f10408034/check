package com.vangood.chat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.vangood.chat.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val TAG = SignUpActivity::class.java.simpleName
    lateinit var binding: ActivitySignUpBinding
    val LoginResultActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ result ->
        Log.d(TAG, "back from SignUPActivity")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bSignupSignup.setOnClickListener {
            val nickname = binding.edSignupNickname.text.toString()
            val account = binding.edSignupAccount.text.toString()
            val password = binding.edSignupPassword.text.toString()

            val pref = getSharedPreferences("check", Context.MODE_PRIVATE)
            pref.edit().putString("nickname", nickname)
                .putString("account", account)
                .putString("password", password)
                .apply()


            LoginResultActivity.launch(Intent(this, LoginActivity::class.java))


        }
    }
}