package com.eltescode.mycalc.domain

sealed interface CalculatorAction {
    data class Number(val number: Int) : CalculatorAction
    data class OperationCalc(val operation: Operation) : CalculatorAction
    object Clear : CalculatorAction
    object Delete : CalculatorAction
    object Parenthesis : CalculatorAction
    object Calculate : CalculatorAction
    object Decimal : CalculatorAction

}