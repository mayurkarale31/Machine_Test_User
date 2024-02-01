package com.bitcodetech.machine_test_user.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bitcodetech.machine_test_user.databinding.UserDetailsActivityBinding
import com.bitcodetech.machine_test_user.models.User

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: UserDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserDetailsActivityBinding.inflate(layoutInflater)

        val message =  intent.getSerializableExtra("users")

        binding.users = message as User?

        setContentView(binding.root)
    }
}