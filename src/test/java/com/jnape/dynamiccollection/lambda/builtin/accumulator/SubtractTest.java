package com.jnape.dynamiccollection.lambda.builtin.accumulator;

import org.junit.Test;
import testsupport.UnsupportedNumber;

import static org.junit.Assert.assertEquals;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class SubtractTest {

    @Test
    public void shouldSubtractNumbers() {
        Subtract subtract = new Subtract();

        assertEquals((byte) 1, subtract.apply((byte) 1, (byte) 0));
        assertEquals((short) 3, subtract.apply((short) 4, (short) 1));
        assertEquals(5, subtract.apply(7, 2));
        assertEquals(7l, subtract.apply(10l, 3l));
        assertEquals(9f, subtract.apply(13f, 4f));
        assertEquals(11d, subtract.apply(16d, 5d));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfSubtractingUnsupportedNumericType() {
        UnsupportedNumber unsupportedNumber = new UnsupportedNumber();
        new Subtract().apply(1, unsupportedNumber);
    }

    @Test
    public void shouldHaveStaticFactoryMethod() {
        assertReflectionEquals(new Subtract(), Subtract.minus());
    }

    @Test
    public void shouldProvideConvenienceMethodForApply() {
        assertEquals(5, Subtract.subtract(7, 2));
    }
}
