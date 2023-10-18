package com.eltescode.mycalc.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ExpressionWriterTest {

    private lateinit var writer: ExpressionWriter


    @Before
    fun setUp(){
        writer = ExpressionWriter()
    }
    @Test
    fun `Initial parenthesis parsed`() {

        writer.processAction(CalculatorAction.Parenthesis)
        writer.processAction(CalculatorAction.Number(5))
        writer.processAction(CalculatorAction.OperationCalc(Operation.ADD))
        writer.processAction(CalculatorAction.Number(4))
        writer.processAction(CalculatorAction.Parenthesis)
        assertThat(writer.expression).isEqualTo("(5+4)")
    }

    @Test
    fun `Closing parenthesis at the start not parsed`() {

        writer.processAction(CalculatorAction.Parenthesis)
        writer.processAction(CalculatorAction.Parenthesis)

        assertThat(writer.expression).isEqualTo("((")
    }

    @Test
    fun `Parenthesis around number are parsed`() {

        writer.processAction(CalculatorAction.Parenthesis)
        writer.processAction(CalculatorAction.Number(6))
        writer.processAction(CalculatorAction.Parenthesis)

        assertThat(writer.expression).isEqualTo("(6)")
    }
}