package com.example.kalkulator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var displayOperation: TextView
    private lateinit var displayResult: TextView
    private var currentInput = ""
    private var operation = ""
    private var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayOperation = findViewById(R.id.operasi)
        displayResult = findViewById(R.id.hasil)

        val buttonIds = listOf(
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
            R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7,
            R.id.btn_8, R.id.btn_9, R.id.btn_tambah, R.id.btn_kurang,
            R.id.btn_x, R.id.btn_miring, R.id.btn_persen, R.id.btn_titik
        )

        buttonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onButtonClick(it as Button) }
        }

        findViewById<Button>(R.id.btn_ac).setOnClickListener { clear() }
        findViewById<Button>(R.id.btn_samadengan).setOnClickListener { calculateResult() }
    }

    private fun onButtonClick(button: Button) {
        val value = button.text.toString()

        if (value in listOf("+", "-", "x", "/", "%")) {
            if (currentInput.isNotEmpty()) {
                result = currentInput.toDouble()
                currentInput = ""
                operation = value
                displayOperation.text = "$result $operation"
            }
        } else {
            currentInput += value
            displayOperation.text = "$result $operation $currentInput"
        }
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && operation.isNotEmpty()) {
            val currentValue = currentInput.toDouble()

            result = when (operation) {
                "+" -> result + currentValue
                "-" -> result - currentValue
                "x" -> result * currentValue
                "/" -> result / currentValue
                "%" -> result % currentValue
                else -> result
            }

            displayResult.text = result.toString()
            displayOperation.text = ""
            currentInput = ""
            operation = ""
        }
    }

    private fun clear() {
        currentInput = ""
        operation = ""
        result = 0.0
        displayOperation.text = ""
        displayResult.text = "0"
    }
}
