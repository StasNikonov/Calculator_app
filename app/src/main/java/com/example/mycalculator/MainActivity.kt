package com.example.mycalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mycalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initListeners()
    }

    fun initListeners() {
        binding.btnOne.setOnClickListener { addInputNumber(1.toString()) }
        binding.btnTwo.setOnClickListener { addInputNumber(2.toString()) }
        binding.btnThree.setOnClickListener { addInputNumber(3.toString()) }
        binding.btnFour.setOnClickListener { addInputNumber(4.toString()) }
        binding.btnFive.setOnClickListener { addInputNumber(5.toString()) }
        binding.btnSix.setOnClickListener { addInputNumber(6.toString()) }
        binding.btnSeven.setOnClickListener { addInputNumber(7.toString()) }
        binding.btnEight.setOnClickListener { addInputNumber(8.toString()) }
        binding.btnNine.setOnClickListener { addInputNumber(9.toString()) }
        binding.btnZero.setOnClickListener { addInputNumber(0.toString()) }
        binding.btnOpenBracket.setOnClickListener { addInputNumber("(") }
        binding.btnCloseBracket.setOnClickListener { addInputNumber(")") }
        binding.btnDivide.setOnClickListener { addInputNumber("/") }
        binding.btnMultiply.setOnClickListener { addInputNumber("*") }
        binding.btnPlus.setOnClickListener { addInputNumber("+") }
        binding.btnMinus.setOnClickListener { addInputNumber("-") }
        binding.btnPoint.setOnClickListener { addInputNumber(".") }

        binding.btnAC.setOnClickListener {
            handleACClick()
        }

        binding.btnDeleteLast.setOnClickListener {
            handleDeleteLastClick()
        }
        binding.btnEqual.setOnClickListener {
            handleEqualClick()
        }
    }

    fun handleACClick() {
        binding.inputView.text = ""
        binding.result.text = ""
    }

    fun handleDeleteLastClick() {
        val tvInputText = binding.inputView.text.toString()
        if (tvInputText.isNotEmpty())
            binding.inputView.text = tvInputText.substring(0, tvInputText.length - 1)
    }

    @SuppressLint("SetTextI18n")
    fun handleEqualClick() {
        try {
            val expression = ExpressionBuilder(binding.inputView.text.toString()).build()
            val result = expression.evaluate()

            binding.result.text = if (result == result.toLong().toDouble()) {
                result.toLong().toString()
            } else {
                result.toString()
            }
        } catch (e: ArithmeticException) {
            binding.result.text = "Division by zero is not possible"
        } catch (e: Exception) {
            Log.e("ERROR_TAG", e.message.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    fun addInputNumber(number: String) {
        val currentInput = binding.inputView.text.toString()

        val lastCharIsOperator = currentInput.isNotEmpty() && "+-/*.".contains(currentInput.last())

        if (!lastCharIsOperator) {
            binding.inputView.text = currentInput + number
        } else if (number !in "+-/*.")
            binding.inputView.text = currentInput + number
    }
}

