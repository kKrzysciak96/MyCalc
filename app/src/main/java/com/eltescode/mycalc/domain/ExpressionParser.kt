package com.eltescode.mycalc.domain

class ExpressionParser(private val calculation: String) {

    fun parse(): List<ExpressionPart> {
        val resultPartsList = mutableListOf<ExpressionPart>()
        var i = 0
        while (i < calculation.length) {
            val currentChar = calculation[i]

            when {
                currentChar in operationSymbols -> resultPartsList
                    .add(ExpressionPart.OperationType(operationFromSymbol(currentChar)))

                currentChar.isDigit() -> {
                    i = parseNumber(startingIndex = i, mutableList = resultPartsList)
                    continue
                }

                currentChar in "()" -> {
                    parseParenthesis(character = currentChar, mutableList = resultPartsList)
                }
            }
            i++
        }
        return resultPartsList
    }

    private fun parseNumber(startingIndex: Int, mutableList: MutableList<ExpressionPart>): Int {
        var i = startingIndex

        val numberAsString = buildString {
            while (i < calculation.length && calculation[i] in "0123456789.") {
                append(calculation[i])
                i++
            }
        }
        mutableList.add(ExpressionPart.Number(numberAsString.toDouble()))
        return i
    }

    private fun parseParenthesis(character: Char, mutableList: MutableList<ExpressionPart>) {
        mutableList.add(
            element = when (character) {
                '(' -> ExpressionPart.Parenthesis(ParenthesisType.Opening)
                else -> ExpressionPart.Parenthesis(ParenthesisType.Closing)
            }
        )
    }
}

