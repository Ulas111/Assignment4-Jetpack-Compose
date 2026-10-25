
package com.example.tiptime

import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.isToggleable
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {

        composeTestRule.setContent {
            TipTimeTheme {
                TipTimeLayout()
            }
        }

        composeTestRule
            .onNodeWithText("Bill Amount")
            .performTextInput("10")

        composeTestRule
            .onNodeWithText("Tip Percentage")
            .performTextInput("20")

        val expectedTip =
            NumberFormat.getCurrencyInstance().format(2)

        composeTestRule
            .onNodeWithText("Tip Amount: $expectedTip")
            .assertExists()
    }

    @Test
    fun calculate_tip_with_round_up() {

        composeTestRule.setContent {
            TipTimeTheme {
                TipTimeLayout()
            }
        }

        composeTestRule
            .onNodeWithText("Bill Amount")
            .performTextInput("53")

        composeTestRule
            .onNodeWithText("Tip Percentage")
            .performTextInput("18")

        val normalTip =
            NumberFormat.getCurrencyInstance().format(9.54)

        composeTestRule
            .onNodeWithText("Tip Amount: $normalTip")
            .assertExists()

        composeTestRule
            .onNode(isToggleable())
            .assertIsOff()

        composeTestRule
            .onNode(isToggleable())
            .performClick()

        composeTestRule
            .onNode(isToggleable())
            .assertIsOn()

        val roundedTip =
            NumberFormat.getCurrencyInstance().format(10)

        composeTestRule
            .onNodeWithText("Tip Amount: $roundedTip")
            .assertExists()
    }

}
