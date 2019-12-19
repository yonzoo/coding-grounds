package com.keksec.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.floor

class MainActivity : AppCompatActivity() {

    var left = ""
    var right = ""
    var operator = ""

    fun getCurrentPartValue(): String {
        return if (operator.length > 0) right else left
    }

    fun appendToCurrentPart(elem: String): Unit {
        if (operator.length > 0) right += elem else left += elem
    }

    fun setCurrentPart(newValue: String): Unit {
        if (operator.length > 0) right = newValue else left = newValue
    }

    fun getExpression(): String {
        return left + operator + right
    }

    fun countExpression(): String {
        return when (operator) {
            "*" -> getValidForm(left.toDouble() * right.toDouble())
            "/" -> if ((floor(right.toDouble()) == right.toDouble()) && floor(right.toDouble()).toInt() == 0) "Ошибка" else getValidForm(
                left.toDouble() / right.toDouble()
            )
            "-" -> getValidForm(left.toDouble() - right.toDouble())
            "+" -> getValidForm(left.toDouble() + right.toDouble())
            "%" -> getValidForm(left.toDouble() % right.toDouble())
            else -> Double.NaN.toString()
        }
    }

    fun tryUpdateData(op: String): Unit {
        if (left.length > 0 && !left.equals("-") && right.length > 0 && !right.equals("-")) {
            val result = countExpression()
            left = if (result.equals("Ошибка")) "" else result
            operator = if (result.equals("Ошибка")) "" else op
            right = ""
            expressionField.text = if (result.equals("Ошибка")) "Ошибка" else getExpression()
        }
    }

    fun getValidForm(result: Double): String {
        var res = result
        if (res == -0.0) res = 0.0
        return if (floor(res) == res) res.toInt().toString()
        else res.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        zeroBtn.setOnClickListener {
            if (!getCurrentPartValue().equals("0")) appendToCurrentPart("0")
            expressionField.text = getExpression()
        }

        oneBtn.setOnClickListener {
            if (!getCurrentPartValue().equals("0") && !getCurrentPartValue().equals("-0")) appendToCurrentPart(
                "1"
            ) else setCurrentPart("1")
            expressionField.text = getExpression()
        }

        twoBtn.setOnClickListener {
            if (!getCurrentPartValue().equals("0") && !getCurrentPartValue().equals("-0")) appendToCurrentPart(
                "2"
            ) else setCurrentPart("2")
            expressionField.text = getExpression()
        }

        threeBtn.setOnClickListener {
            if (!getCurrentPartValue().equals("0") && !getCurrentPartValue().equals("-0")) appendToCurrentPart(
                "3"
            ) else setCurrentPart("3")
            expressionField.text = getExpression()
        }

        fourBtn.setOnClickListener {
            if (!getCurrentPartValue().equals("0") && !getCurrentPartValue().equals("-0")) appendToCurrentPart(
                "4"
            ) else setCurrentPart("4")
            expressionField.text = getExpression()
        }

        fiveBtn.setOnClickListener {
            if (!getCurrentPartValue().equals("0") && !getCurrentPartValue().equals("-0")) appendToCurrentPart(
                "5"
            ) else setCurrentPart("5")
            expressionField.text = getExpression()
        }
        sixBtn.setOnClickListener {
            if (!getCurrentPartValue().equals("0") && !getCurrentPartValue().equals("-0")) appendToCurrentPart(
                "6"
            ) else setCurrentPart("6")
            expressionField.text = getExpression()
        }
        sevenBtn.setOnClickListener {
            if (!getCurrentPartValue().equals("0") && !getCurrentPartValue().equals("-0")) appendToCurrentPart(
                "7"
            ) else setCurrentPart("7")
            expressionField.text = getExpression()
        }
        eightBtn.setOnClickListener {
            if (!getCurrentPartValue().equals("0") && !getCurrentPartValue().equals("-0")) appendToCurrentPart(
                "8"
            ) else setCurrentPart("8")
            expressionField.text = getExpression()
        }
        nineBtn.setOnClickListener {
            if (!getCurrentPartValue().equals("0") && !getCurrentPartValue().equals("-0")) appendToCurrentPart(
                "9"
            ) else setCurrentPart("9")
            expressionField.text = getExpression()
        }
        commaBtn.setOnClickListener {
            if (getCurrentPartValue().length > 0 && !getCurrentPartValue().contains(".")) appendToCurrentPart(".")
            expressionField.text = getExpression()
        }

        delBtn.setOnClickListener {
            if (getExpression().length > 0) {
                val current = getCurrentPartValue()
                if (current.length == 0) operator = "" else setCurrentPart(
                    current.substring(
                        0,
                        current.length - 1
                    )
                )
                expressionField.text = getExpression();
            }
        }

        clearBtn.setOnClickListener {
            left = ""
            right = ""
            operator = ""
            expressionField.text = getExpression()
        }

        multBtn.setOnClickListener {
            if (left.length > 0 && !left.equals("-") && right.length == 0) {
                operator = "*"
                expressionField.text = getExpression()
            } else tryUpdateData("*")
        }

        divBtn.setOnClickListener {
            if (left.length > 0 && !left.equals("-") && right.length == 0) {
                operator = "/"
                expressionField.text = getExpression()
            } else tryUpdateData("/")
        }

        plusBtn.setOnClickListener {
            if (left.length > 0 && !left.equals("-") && right.length == 0) {
                operator = "+"
                expressionField.text = getExpression()
            } else tryUpdateData("+")
        }
        minusBtn.setOnClickListener {
            if (((operator == "*" || operator == "/" || operator == "%") && right.length == 0) || left.length == 0) {
                appendToCurrentPart("-")
                expressionField.text = getExpression()
            } else if (left.length > 0 && !left.equals("-") && right.length == 0) {
                operator = "-"
                expressionField.text = getExpression()
            } else tryUpdateData("-")
        }
        percentageBtn.setOnClickListener {
            if (left.length > 0 && !left.equals("-") && right.length == 0) {
                operator = "%"
                expressionField.text = getExpression()
            } else tryUpdateData("%")
        }
        equalBtn.setOnClickListener {
            val current = getCurrentPartValue()
            if (current.takeLast(1).equals(".")) setCurrentPart(
                current.substring(0, current.length - 1)
            )
            if (left.length > 0 && !left.equals("-") && right.length > 0 && !right.equals("-")) {
                val result = countExpression()
                left = if (result.equals("Ошибка")) "" else result
                operator = ""
                right = ""
                expressionField.text = if (result.equals("Ошибка")) "Ошибка" else result
            } else expressionField.text = left
        }
    }
}
