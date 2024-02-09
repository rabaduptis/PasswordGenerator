package com.root14.passwordgenerator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.snackbar.Snackbar
import com.root14.passwordgenerator.databinding.ActivityMainBinding
import com.root14.passwordgenerator.util.ViewUtil
import com.root14.passwordgenerator.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var switchs: MutableList<MaterialSwitch>

    val mainViewModel: MainViewModel by viewModels<MainViewModel>()

    private var switchCounter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //handle view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set view
        enableEdgeToEdge()
        ViewUtil.setInset(findViewById(R.id.main))
        switchs = getSwitchesList()

        binding.sliderPswLength.addOnChangeListener { slider, value, b ->
            binding.textViewPswLength.text = value.toInt().toString()
        }


        //initial generated password
        val passwordGenerator =
            PasswordGeneratorBuilder() // Customize character sets and length as needed
                .setMaxLength(16).build()

        val password = passwordGenerator.generatePassword()

        binding.textViewPasswordHolder.text = password
        binding.switchPin.isChecked = true

        binding.imageButtonCopy.setOnClickListener {
            mainViewModel.copy2ClipBoard(binding.textViewPasswordHolder.text.toString())
                .observe(this) {
                    if (it) {
                        Snackbar.make(
                            binding.root,
                            "Copied value to the clipboard \n${binding.textViewPasswordHolder.text}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        binding.imageButtonRefresh.setOnClickListener {
            val switchesValueArr = arrayListOf<Boolean>()
            switchs.forEach {
                switchesValueArr.add(it.isChecked)
            }

            //The number generation property of the password generator object was used to generate pins.
            val pinValue = if (!switchesValueArr.contains(true)) {
                true
            } else {
                switchesValueArr[2]
            }

            val builder = PasswordGeneratorBuilder().apply {
                enableUppercase(switchesValueArr[0])
                enableLowerCase(switchesValueArr[1])
                enableNumbers(pinValue)
                enableSymbols(switchesValueArr[3])
                setMaxLength(binding.sliderPswLength.value.toInt())
            }

            binding.textViewPasswordHolder.text = builder.build().generatePassword()

        }

        //TODO implement password strength checker algorithm

        binding.switchPin.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                switchs.forEach {
                    it.isChecked = false
                }
            } else {
                if (switchCounter <= 0) binding.switchLowerCase.isChecked = true
            }
        }

        switchs.forEach {
            it.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    switchCounter++
                    if (binding.switchPin.isChecked) {
                        binding.switchPin.isChecked = false
                    }
                } else {
                    switchCounter--
                }

                if (switchCounter <= 0) binding.switchPin.isChecked = true
            }
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