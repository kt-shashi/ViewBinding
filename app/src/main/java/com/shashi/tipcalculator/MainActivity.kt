package com.shashi.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shashi.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonResult.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {

        val costInString: String = binding.editTextCost.text.toString()
        val cost: Double? = costInString.toDoubleOrNull()

        if (cost == null) {
            binding.textViewResult.text = ""
            return
        }

        val selectedId: Int = binding.tipOptions.checkedRadioButtonId
        val tipPercent: Double = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip: Double = tipPercent * cost

        val roundup: Boolean = binding.switchRoundUp.isChecked
        if (roundup)
            tip = ceil(tip)

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.textViewResult.text = getString(R.string.tip_amount, formattedTip)
    }
}