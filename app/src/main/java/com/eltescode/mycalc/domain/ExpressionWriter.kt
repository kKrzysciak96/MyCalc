package com.eltescode.mycalc.domain

class ExpressionWriter {

    var expression = ""
    fun processAction(action: CalculatorAction) {
        when (action) {
            CalculatorAction.Calculate -> {
                val parser = ExpressionParser(prepareForCalculation())
                val evaluator = ExpressionEvaluator(parser.parse())
                expression = evaluator.evaluate().toString()
            }

            CalculatorAction.Clear -> {
                expression = ""
            }

            CalculatorAction.Decimal -> {
                if (canEnterDecimal()) {
                    expression += "."
                }
            }

            CalculatorAction.Delete -> {
                expression.dropLast(1)
            }

            is CalculatorAction.Number -> {
                expression += action.number
            }

            is CalculatorAction.OperationCalc -> {
                if (canEnterOperation(action.operation)) {
                    expression += action.operation.symbol
                }
            }

            CalculatorAction.Parenthesis -> {
                processParenthesis()
            }
        }
    }

    private fun prepareForCalculation(): String {
        val newExpression = expression.takeLastWhile { it in "$operationSymbols(." }
        if (newExpression.isEmpty()) {
            return "0"
        }
        return newExpression
    }

    private fun processParenthesis() {
        val openingCount = expression.count { it == '(' }
        val closingCount = expression.count { it == ')' }
        expression += when {
            expression.isEmpty() || expression.last() in "$operationSymbols(" -> {
                '('
            }

            expression.last() in "0123456789)" && openingCount == closingCount -> return
            else -> {
                ')'
            }
        }
    }

    private fun canEnterDecimal(): Boolean {
        if (expression.isEmpty() || expression.last() in "$operationSymbols.()") {
            return false
        }
        return !expression.takeLastWhile {
            it in "0123456789."
        }.contains(".")
    }

    private fun canEnterOperation(operation: Operation): Boolean {
        if (operation in listOf(Operation.ADD, Operation.SUBTRACT)) {
            return expression.isEmpty() || expression.last() in "$operationSymbols()0123456789"
        }
        return expression.isNotEmpty() || expression.last() in "0123456789)"
    }
}