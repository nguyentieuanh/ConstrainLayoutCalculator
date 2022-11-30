package com.example.constrainlayoutcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<TextView>(R.id.editText)
        val buCE = findViewById<AppCompatButton>(R.id.buCE)
        val buC = findViewById<AppCompatButton>(R.id.buC)
        val buBS = findViewById<AppCompatButton>(R.id.buBS)
        val buDivide = findViewById<AppCompatButton>(R.id.buDivide)
        val buPlus = findViewById<AppCompatButton>(R.id.buPlus)
        val buMinus = findViewById<AppCompatButton>(R.id.buMinus)
        val buTimes = findViewById<AppCompatButton>(R.id.buTimes)
        val buEqual = findViewById<AppCompatButton>(R.id.buEqual)
        val buPoint = findViewById<AppCompatButton>(R.id.buPoint)
        val bu7 = findViewById<AppCompatButton>(R.id.bu7)
        val bu8 = findViewById<AppCompatButton>(R.id.bu8)
        val bu9 = findViewById<AppCompatButton>(R.id.bu9)
        val bu6 = findViewById<AppCompatButton>(R.id.bu6)
        val bu5 = findViewById<AppCompatButton>(R.id.bu5)
        val bu4 = findViewById<AppCompatButton>(R.id.bu4)
        val bu3 = findViewById<AppCompatButton>(R.id.bu3)
        val bu2 = findViewById<AppCompatButton>(R.id.bu2)
        val bu1 = findViewById<AppCompatButton>(R.id.bu1)
        val bu0 = findViewById<AppCompatButton>(R.id.bu0)

        var canClear = true

        bu1.setOnClickListener(){
            if (canClear) {
                editText.text = "1"
            } else {
                editText.text = (editText.text.toString() + "1")
            }
            canClear = false
        }

        bu2.setOnClickListener(){
            if (canClear) {
                editText.text = "2"
            }else {
                editText.text = (editText.text.toString() + "2")
            }
            canClear = false
        }

        bu3.setOnClickListener(){
            if (canClear) {
                editText.text = "3"
            }else {
                editText.text = (editText.text.toString() + "3")
            }
            canClear = false
        }

        bu4.setOnClickListener(){
            if (canClear) {
                editText.text = "4"
            }else {
                editText.text = (editText.text.toString() + "4")
            }
            canClear = false
        }

        bu5.setOnClickListener(){
            if (canClear) {
                editText.text = "5"
            }else {
                editText.text = (editText.text.toString() + "5")
            }
            canClear = false
        }

        bu6.setOnClickListener(){
            if (canClear) {
                editText.text = "6"
            }else {
                editText.text = (editText.text.toString() + "6")
            }
            canClear = false
        }

        bu7.setOnClickListener(){
            if (canClear) {
                editText.text = "7"
            }else {
                editText.text = (editText.text.toString() + "7")
            }
            canClear = false
        }

        bu8.setOnClickListener(){
            if (canClear) {
                editText.text = "8"
            }else {
                editText.text = (editText.text.toString() + "8")
            }
            canClear = false
        }

        bu9.setOnClickListener(){
            if (canClear) {
                editText.text = "9"
            }else {
                editText.text = (editText.text.toString() + "9")
            }
            canClear = false
        }

        bu0.setOnClickListener(){
            if (canClear) {
                editText.text = "0"
            }else {
                editText.text = (editText.text.toString() + "0")
            }
            canClear = false
        }

        buPlus.setOnClickListener(){
            editText.text = (editText.text.toString() + "+")
        }

        buMinus.setOnClickListener(){
            editText.text = (editText.text.toString() + "-")
        }

        buDivide.setOnClickListener(){
            editText.text = (editText.text.toString() + "/")
        }

        buTimes.setOnClickListener(){
            editText.text = (editText.text.toString() + "x")
        }

        buEqual.setOnClickListener(){
            val str: String = editText.text.toString()
            val result: Double = evaluate(str)
            val r = result.toString()
            editText.text = r
        }

    }

    fun evaluate(str: String): Double {
        return object : Any() {
            // on below line we are creating variable
            // for tracking the position and char pos.
            var pos = -1
            var ch = 0

            // below method is for moving to next character.
            fun nextChar() {
                // on below line we are incrementing our position
                // and moving it to next position.
                ch = if (++pos < str.length) str[pos].toInt() else -1
            }

            // this method is use to check the extra space
            // present int the expression and removing it.
            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.toInt()) nextChar()
                // on below line we are checking the char pos
                // if both is equal then we are returning it to true.
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            // below method is to parse our
            // expression and to get the ans
            // in this we are calling a parse
            // expression method to calculate the value.
            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            // in this method we will only perform addition and
            // subtraction operation on the expression.
            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.toInt())) x += parseTerm() // addition
                    else if (eat('-'.toInt())) x -= parseTerm() // subtraction
                    else return x
                }
            }

            // in below method we will perform
            // only multiplication and division operation.
            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.toInt())) x *= parseFactor() // multiplication
                    else if (eat('/'.toInt())) x /= parseFactor() // division
                    else return x
                }
            }

            // below method is use to parse the factor
            fun parseFactor(): Double {
                //on below line we are checking for addition
                // and subtraction and performing unary operations.
                if (eat('+'.toInt())) return parseFactor() // unary plus
                if (eat('-'.toInt())) return -parseFactor() // unary minus
                // creating a double variable for ans.
                var x: Double
                // on below line we are creating
                // a variable for position.
                val startPos = pos
                // on below line we are checking
                // for opening and closing parenthesis.
                if (eat('('.toInt())) { // parentheses
                    x = parseExpression()
                    eat(')'.toInt())
                } else if (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) {
                    // numbers
                    while (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) nextChar()
                    // on below line we are getting sub string from our string using start and pos.
                    x = str.substring(startPos, pos).toDouble()
                } else if (ch >= 'a'.toInt() && ch <= 'z'.toInt()) {
                    // on below function we are checking for the operator in our expression.
                    while (ch >= 'a'.toInt() && ch <= 'z'.toInt()) nextChar()
                    val func = str.substring(startPos, pos)
                    // calling a method to parse our factor.
                    x = parseFactor()
                    // on below line we are checking for square root.
                    x =
                        if (func == "sqrt") Math.sqrt(x)
                        // on below line we are checking for sin function
                        // and calculating sin function using Math class.
                        else if (func == "sin") Math.sin(
                            Math.toRadians(x)
                            // on below line we are calculating the cos value
                        ) else if (func == "cos") Math.cos(
                            Math.toRadians(x)
                            // on below line we are calculating
                            // the tan value of our expression.
                        ) else if (func == "tan")
                            Math.tan(Math.toRadians(x))
                        // on below line we are calculating
                        // log value of the expression.
                        else if (func == "log")
                            Math.log10(x)
                        // on below line we are calculating
                        // ln value of expression.
                        else if (func == "ln") Math.log(x)
                        // f we get any error then
                        // we simply return the exception.
                        else throw RuntimeException(
                            "Unknown function: $func"
                        )
                } else {
                    // if the condition not satisfy then we are returning the exception
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                // on below line we are calculating the power of the expression.
                if (eat('^'.toInt())) x = Math.pow(x, parseFactor()) // exponentiation
                return x
            }
            // at last calling a parse for our expression.
        }.parse()
    }
}