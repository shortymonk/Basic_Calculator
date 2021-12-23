package org.hyperskill.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0
    private var operator = ""
    private var tmp = ""
    private var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.editText)

        clearButton.setOnClickListener {
            textView.apply {
                text = ""
                hint = "0"
            }
            operator = ""
            tmp = ""
            0.0.also { result = it; operand1 = it; operand2 = it }
        }

        multiplyButton.setOnClickListener {
            operand1 = tmp.toDouble()
            operator = "x"
            textView.text = tmp
        }

        divideButton.setOnClickListener {
            getOperation("÷")
        }

        addButton.setOnClickListener {
            getOperation("+")
        }

        subtractButton.setOnClickListener {
            if (tmp == "") {
                tmp = "-"
                textView.text = tmp
            } else { getOperation("-") }
        }

        multiplyButton.setOnClickListener {
            getOperation("x")
        }

        equalButton.setOnClickListener {
            operand2 = if (tmp == "") 0.0 else tmp.toDouble()
            when (operator) {
                "+" -> result = operand1 + operand2
                "-" -> result = operand1 - operand2
                "x" -> result = operand1 * operand2
                "÷" -> result = if (operand2 != 0.0) operand1 / operand2 else return@setOnClickListener
            }
            textView.text = getStringForView(result)
            operand1 = result
            tmp = ""
            result = 0.0
        }

        dotButton.setOnClickListener {
            if (tmp.isEmpty() || tmp == "-") {
                tmp += "0."
            } else {
                if ("." !in tmp) tmp += "."
            }
            textView.text = tmp
        }

        button0.setOnClickListener {
        if (textView.text.toString() != "0" && textView.text.toString() != "-0") {
                tmp += "0"
            }
            textView.text = tmp
        }

        button1.setOnClickListener { addChar(button1) }

        button2.setOnClickListener { addChar(button2) }

        button3.setOnClickListener { addChar(button3) }

        button4.setOnClickListener { addChar(button4) }

        button5.setOnClickListener { addChar(button5) }

        button6.setOnClickListener { addChar(button6) }

        button7.setOnClickListener { addChar(button7) }

        button8.setOnClickListener { addChar(button8) }

        button9.setOnClickListener { addChar(button9) }
    }

    private fun addChar(button: Button) {
        if (tmp.length == 1 && tmp.first() == '0') tmp = button.text.toString() else tmp += button.text
        textView.text = tmp
    }

    private fun getStringForView(num: Double): String {
        return if (num - num.toInt() == 0.0) num.toInt().toString() else num.toString()
    }

    private fun getOperation(op: String) {
        if (tmp.isNotEmpty()) operand1 = tmp.toDouble()
        operator = op
        textView.apply {
            hint = getStringForView(operand1)
            text = ""
        }
        tmp = ""
    }

}