package com.jnape.dynamiccollection.lambda.dyadic.builtin;

import org.junit.Test;
import testsupport.UnsupportedNumber;

import static com.jnape.dynamiccollection.lambda.dyadic.builtin.Divide.divide;
import static org.junit.Assert.assertEquals;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class DivideTest {

    @Test
    public void shouldDivideNumbers() {
        Divide<Number> divide = new Divide<Number>();

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
        new Divide<Number>().apply(1, unsupportedNumber);
    }

    @Test
    public void shouldHaveStaticFactoryMethod() {
        assertReflectionEquals(new Divide<Number>(), Divide.dividedBy());
    }

    @Test
    public void shouldProvideConvenienceMethodForApply() {
        assertEquals((Integer) 2, divide(10, 5));
    }
}
