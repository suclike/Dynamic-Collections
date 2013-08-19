package com.jnape.dynamiccollection.lambda.builtin.accumulator;

import org.junit.Test;
import testsupport.UnsupportedNumber;

import static org.junit.Assert.assertEquals;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class DivideTest {

    @Test
    public void shouldDivideNumbers() {
        Divide divide = new Divide();

        assertEquals((byte) 96, divide.apply((byte) 96, (byte) 1));
        assertEquals((short) 48, divide.apply((short) 96, (short) 2));
        assertEquals(32, divide.apply(96, 3));
        assertEquals(24l, divide.apply(96l, 4l));
        assertEquals(16f, divide.apply(96f, 6f));
        assertEquals(12d, divide.apply(96d, 8d));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfDividingUnsupportedNumericType() {
        UnsupportedNumber unsupportedNumber = new UnsupportedNumber();
        new Divide().apply(1, unsupportedNumber);
    }

    @Test
    public void shouldHaveStaticFactoryMethod() {
        assertReflectionEquals(new Divide(), Divide.divided_by());
    }

    @Test
    public void shouldProvideConvenienceMethodForApply() {
        assertEquals(2, Divide.divide(10, 5));
    }
}
