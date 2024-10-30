package com.example.integerlistapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNumber = findViewById<EditText>(R.id.etNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnShow = findViewById<Button>(R.id.btnShow)
        val listView = findViewById<ListView>(R.id.listView)
        val tvError = findViewById<TextView>(R.id.tvError)

        btnShow.setOnClickListener {
            val numberInput = etNumber.text.toString()
            tvError.text = ""

            if (numberInput.isEmpty()) {
                tvError.text = "Vui lòng nhập số nguyên dương"
                return@setOnClickListener
            }

            val n = numberInput.toIntOrNull()
            if (n == null || n <= 0) {
                tvError.text = "Số không hợp lệ. Vui lòng nhập số nguyên dương"
                return@setOnClickListener
            }

            val results = when (radioGroup.checkedRadioButtonId) {
                R.id.rbEven -> getEvenNumbers(n)
                R.id.rbOdd -> getOddNumbers(n)
                R.id.rbSquare -> getSquareNumbers(n)
                else -> emptyList()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
            listView.adapter = adapter
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        val squareNumbers = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squareNumbers.add(i * i)
            i++
        }
        return squareNumbers
    }
}
