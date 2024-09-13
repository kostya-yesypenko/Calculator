package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity

class FuelOilCalculatorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fuel_oil_calculator)

        val editTextC: EditText = findViewById(R.id.editTextC)
        val editTextH: EditText = findViewById(R.id.editTextH)
        val editTextO: EditText = findViewById(R.id.editTextO)
        val editTextS: EditText = findViewById(R.id.editTextS)
        val editTextQi: EditText = findViewById(R.id.editTextQi)
        val editTextW: EditText = findViewById(R.id.editTextW)
        val editTextA: EditText = findViewById(R.id.editTextA)
        val editTextV: EditText = findViewById(R.id.editTextV)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val switchToFuelCompButton: Button = findViewById(R.id.switchToFuelCompButton)

        calculateButton.setOnClickListener {
            val c = editTextC.text.toString().toDoubleOrNull() ?: 0.0
            val h = editTextH.text.toString().toDoubleOrNull() ?: 0.0
            val o = editTextO.text.toString().toDoubleOrNull() ?: 0.0
            val s = editTextS.text.toString().toDoubleOrNull() ?: 0.0
            val qi = editTextQi.text.toString().toDoubleOrNull() ?: 0.0
            val w = editTextW.text.toString().toDoubleOrNull() ?: 0.0
            val a = editTextA.text.toString().toDoubleOrNull() ?: 0.0
            val v = editTextV.text.toString().toDoubleOrNull() ?: 0.0

            val kRs = (100 - w) / 100
            val kRg = (100 - w - a)/ 100

            val cR = c * kRg
            val hR = h * kRg
            val oR = o * kRg
            val sR = s * kRg
            val ash = a * kRs
            val vR = v * kRs

            val qrG = qi* kRg-0.025*w

            val result = """
                2.1. Склад робочої маси: CР=${"%.2f".format(cR)}% HР=${"%.2f".format(hR)}% OР=${"%.2f".format(oR)}% SР=${"%.2f".format(sR)}% AР=${"%.2f".format(ash)}% VР=${"%.2f".format(vR)} мг/кг
                2.2. Нижча теплота згоряння для робочої маси: ${"%.2f".format(qrG)} МДж/кг
            """.trimIndent()

            resultTextView.text = result
        }

        switchToFuelCompButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
