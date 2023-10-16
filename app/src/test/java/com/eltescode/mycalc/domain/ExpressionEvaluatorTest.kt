package com.eltescode.mycalc.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ExpressionEvaluatorTest {
    private lateinit var evaluator: ExpressionEvaluator

    @Test
    fun `Simple expression properly evaluated`() {
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.0),
                ExpressionPart.OperationType(Operation.ADD),
                ExpressionPart.Number(5.0),
                ExpressionPart.OperationType(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.OperationType(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.OperationType(Operation.DIVIDE),
                ExpressionPart.Number(3.0),
            )
        )
        assertThat(evaluator.evaluate()).isEqualTo(4)
    }

    @Test
    fun `Expression with decimals properly evaluated`() {
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.5),
                ExpressionPart.OperationType(Operation.ADD),
                ExpressionPart.Number(5.5),
                ExpressionPart.OperationType(Operation.SUBTRACT),
                ExpressionPart.Number(3.5),
                ExpressionPart.OperationType(Operation.MULTIPLY),
                ExpressionPart.Number(5.5),
                ExpressionPart.OperationType(Operation.DIVIDE),
                ExpressionPart.Number(3.5)
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(4.5)
    }

    @Test
    fun `Expression with parenthesis properly evaluated`() {
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.0),
                ExpressionPart.OperationType(Operation.ADD),
                ExpressionPart.Parenthesis(ParenthesisType.Opening),
                ExpressionPart.Number(5.0),
                ExpressionPart.OperationType(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Parenthesis(ParenthesisType.Closing),
                ExpressionPart.OperationType(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.OperationType(Operation.DIVIDE),
                ExpressionPart.Number(4.0),
            )
        )
        assertThat(evaluator.evaluate()).isEqualTo(6.5)
    }
}