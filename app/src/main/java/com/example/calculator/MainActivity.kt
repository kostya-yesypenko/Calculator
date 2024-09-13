package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextHw: EditText = findViewById(R.id.editTextHw)
        val editTextCw: EditText = findViewById(R.id.editTextCw)
        val editTextSw: EditText = findViewById(R.id.editTextSw)
        val editTextNw: EditText = findViewById(R.id.editTextNw)
        val editTextOw: EditText = findViewById(R.id.editTextOw)
        val editTextW: EditText = findViewById(R.id.editTextW)
        val editTextA: EditText = findViewById(R.id.editTextA)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val switchToFuelOilButton: Button = findViewById(R.id.switchToFuelOilButton)

        calculateButton.setOnClickListener {
            val hw = editTextHw.text.toString().toDoubleOrNull() ?: 0.0
            val cw = editTextCw.text.toString().toDoubleOrNull() ?: 0.0
            val sw = editTextSw.text.toString().toDoubleOrNull() ?: 0.0
            val nw = editTextNw.text.toString().toDoubleOrNull() ?: 0.0
            val ow = editTextOw.text.toString().toDoubleOrNull() ?: 0.0
            val w = editTextW.text.toString().toDoubleOrNull() ?: 0.0
            val a = editTextA.text.toString().toDoubleOrNull() ?: 0.0

            val kRs = 100 / (100 - w)
            val kRg = 100 / (100 - w - a)

            val hs = hw * kRs
            val cs = cw * kRs
            val ss = sw * kRs
            val ns = nw * kRs
            val os = ow * kRs
            val ash = a * kRs

            val hg = hw * kRg
            val cg = cw * kRg
            val sg = sw * kRg
            val ng = nw * kRg
            val og = ow * kRg

            val qrN = 339 * cw + 1030 * hw - 108.8 * (ow - sw) - 25 * w
            val qrN_MJ = qrN / 1000

            val qrS = (qrN_MJ + 0.025 * w) * kRs
            val qrG = (qrN_MJ + 0.025 * w) * kRg

            val result = """
                1.1. Coefficient from working to dry mass: ${"%.2f".format(kRs)}
                1.2. Coefficient from working to combustible mass: ${"%.2f".format(kRg)}
                1.3. Dry mass composition: H=${"%.2f".format(hs)}% C=${"%.2f".format(cs)}% S=${"%.2f".format(ss)}% N=${"%.2f".format(ns)}% O=${"%.2f".format(os)}% A=${"%.2f".format(ash)}%
                1.4. Combustible mass composition: H=${"%.2f".format(hg)}% C=${"%.2f".format(cg)}% S=${"%.2f".format(sg)}% N=${"%.2f".format(ng)}% O=${"%.2f".format(og)}%
                1.5. Lower heating value for working mass: ${"%.4f".format(qrN_MJ)} MJ/kg
                1.6. Lower heating value for dry mass: ${"%.4f".format(qrS)} MJ/kg
                1.7. Lower heating value for combustible mass: ${"%.4f".format(qrG)} MJ/kg
            """.trimIndent()

            resultTextView.text = result
        }

        switchToFuelOilButton.setOnClickListener {
            val intent = Intent(this, FuelOilCalculatorActivity::class.java)
            startActivity(intent)
        }
    }
}
