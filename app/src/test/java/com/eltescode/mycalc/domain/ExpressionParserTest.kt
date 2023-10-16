package com.eltescode.mycalc.domain


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `Simple expression is properly parsed`() {
        parser = ExpressionParser("3+5-3x4/3")
        val actual = parser.parse()
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.OperationType(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.OperationType(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.OperationType(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.OperationType(Operation.DIVIDE),
            ExpressionPart.Number(3.0)
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Expression with parenthesis is properly parsed`() {
        parser = ExpressionParser("4-(4x5)")
        val actual = parser.parse()
        val expected = listOf(
            ExpressionPart.Number(4.0),
            ExpressionPart.OperationType(Operation.SUBTRACT),
            ExpressionPart.Parenthesis(ParenthesisType.Opening),
            ExpressionPart.Number(4.0),
            ExpressionPart.OperationType(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
            ExpressionPart.Parenthesis(ParenthesisType.Closing),
        )
        assertThat(actual).isEqualTo(expected)
    }
}