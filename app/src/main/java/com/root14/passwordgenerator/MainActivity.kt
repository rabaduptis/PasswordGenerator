package com.root14.passwordgenerator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.snackbar.Snackbar
import com.root14.passwordgenerator.databinding.ActivityMainBinding
import com.root14.passwordgenerator.util.ViewUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var switchs: MutableList<MaterialSwitch>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //handle view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set view
        enableEdgeToEdge()
        ViewUtil.setInset(findViewById(R.id.main))
        switchs = getSwitchesList()

        binding.imageButtonCopy.setOnClickListener {
            Snackbar.make(binding.root, "yep, you clicked!", Snackbar.LENGTH_SHORT).show()
        }
        //TODO implement password strength checker algorithm

        val passwordGenerator =
            PasswordGeneratorBuilder().enableNumbers(false) // Customize character sets and length as needed
                .setMaxLength(16).build()

        val password = passwordGenerator.generatePassword()
        println("Generated password: $password")

        binding.switchPin.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                switchs.forEach {
                    it.isChecked = false
                }
            }
        }

        switchs.forEach {
            it.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    if (binding.switchPin.isChecked) {
                        binding.switchPin.isChecked = false
                    }
                }
            }
        }

        binding.imageButtonCopy.setOnClickListener {

        }

    }

    private fun getSwitchesList(): MutableList<MaterialSwitch> {
        return arrayListOf(
            findViewById(R.id.switch_upperCase),
            findViewById(R.id.switch_lowerCase),
            findViewById(R.id.switch_numbers),
            findViewById(R.id.switch_symbols)
        )
    }
}