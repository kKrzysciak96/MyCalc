package com.eltescode.mycalc.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.eltescode.mycalc.domain.CalculatorAction
import com.eltescode.mycalc.domain.ExpressionWriter

class CalculatorViewModel(private val writer: ExpressionWriter = ExpressionWriter()) : ViewModel() {

    var expression by mutableStateOf("")
        private set

    fun onAction(action: CalculatorAction) {
        writer.processAction(action)
        this.expression = writer.expression
    }
}