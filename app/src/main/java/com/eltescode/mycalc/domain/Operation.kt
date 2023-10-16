package com.eltescode.mycalc.domain

enum class Operation(val symbol : Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('x'),
    DIVIDE('/'),
    PERCENT('%')
}

val operationSymbols = Operation.values().map { it.symbol }.joinToString("")

fun operationFromSymbol(symbol: Char):Operation{
    return Operation.values().find { symbol == it.symbol } ?: throw IllegalSymbol()
}


class IllegalSymbol(message: String = "Invalid symbol") : Throwable(message)