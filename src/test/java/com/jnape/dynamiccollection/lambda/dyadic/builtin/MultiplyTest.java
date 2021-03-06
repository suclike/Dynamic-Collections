package com.jnape.dynamiccollection.lambda.dyadic.builtin;

import org.junit.Test;
import testsupport.UnsupportedNumber;

import static org.junit.Assert.assertEquals;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class MultiplyTest {

    @Test
    public void shouldMultiplyNumbers() {
        Multiply<Number> multiply = new Multiply<Number>();

        assertEquals((byte) 96, multiply.apply((byte) 1, (byte) 96));
        assertEquals((short) 96, multiply.apply((short) 2, (short) 48));
        assertEquals(96, multiply.apply(3, 32));
        assertEquals(96l, multiply.apply(4l, 24l));
        assertEquals(96f, multiply.apply(6f, 16f));
        assertEquals(96d, multiply.apply(8d, 12d));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfMultiplyingUnsupportedNumericType() {
        UnsupportedNumber unsupportedNumber = new UnsupportedNumber();
        new Multiply<Number>().apply(1, unsupportedNumber);
    }

    @Test
    public void shouldHaveStaticFactoryMethod() {
        assertReflectionEquals(new Multiply<Number>(), Multiply.times());
    }

    @Test
    public void shouldProvideConvenienceMethodForApply() {
        assertEquals((Integer) 10, Multiply.multiply(5, 2));
    }
}
