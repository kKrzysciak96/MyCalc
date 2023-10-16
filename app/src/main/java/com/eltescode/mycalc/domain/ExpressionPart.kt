package com.eltescode.mycalc.domain

sealed interface ExpressionPart {
    data class Number(val number: Double) : ExpressionPart
    data class OperationType(val operation: Operation) : ExpressionPart
    data class Parenthesis(val type: ParenthesisType) : ExpressionPart
}

sealed interface ParenthesisType {
    object Opening : ParenthesisType
    object Closing : ParenthesisType
}