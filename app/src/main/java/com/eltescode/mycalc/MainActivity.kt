package com.eltescode.mycalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eltescode.mycalc.presentation.CalculatorScreen
import com.eltescode.mycalc.ui.theme.MyCalcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCalcTheme {
                CalculatorScreen()
            }
        }
    }
}

