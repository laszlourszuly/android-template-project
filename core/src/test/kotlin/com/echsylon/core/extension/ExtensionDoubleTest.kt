package com.echsylon.core.extension

import org.amshove.kluent.`should be equal to`
import org.junit.Test

class ExtensionDoubleTest {

    @Test
    fun `When rounding value less than range min value, the min value is returned`() {
        2.0.roundToIntBetween(3..5) `should be equal to` 3
    }

    @Test
    fun `When rounding value greater than range max value, the max value is returned`() {
        5.0.roundToIntBetween(3..5) `should be equal to` 5
    }

    @Test
    fun `When rounding value within given range, the expected value is returned`() {
        3.5.roundToIntBetween(3..5) `should be equal to` 4
    }

}