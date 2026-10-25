
package com.example.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculateTip_20PercentNoRoundup() {
        val amount = 10.0
        val tipPercent = 20.0

        val expectedTip =
            NumberFormat.getCurrencyInstance().format(2)

        val actualTip = calculateTip(
            amount = amount,
            tipPercent = tipPercent,
            roundUp = false
        )

        assertEquals(expectedTip, actualTip)
    }

    @Test
    fun calculateTip_10PercentWithRoundup() {
        val expectedTip =
            NumberFormat.getCurrencyInstance().format(6)

        val actualTip = calculateTip(
            amount = 53.0,
            tipPercent = 10.0,
            roundUp = true
        )

        assertEquals(expectedTip, actualTip)
    }
}
