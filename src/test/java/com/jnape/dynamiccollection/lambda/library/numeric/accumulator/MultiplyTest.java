package com.jnape.dynamiccollection.lambda.library.numeric.accumulator;

import com.jnape.dynamiccollection.lambda.Function;
import org.junit.Test;
import testsupport.UnsupportedNumber;

import static org.junit.Assert.assertEquals;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class MultiplyTest {

    @Test
    public void shouldMultiplyNumbers() {
        Multiply multiply = new Multiply();

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
        new Multiply().apply(1, unsupportedNumber);
    }

    @Test
    public void shouldHaveStaticFactoryMethod() {
        assertReflectionEquals(new Multiply(), Multiply.times());
    }

    @Test
    public void shouldHaveStaticFactoryMethodForCurriedFunction() {
        Function<Number, Number> times2 = Multiply.times(2);
        assertEquals(10, times2.apply(5));
    }

    @Test
    public void shouldProvideConvenienceMethodForApply() {
        assertEquals(10, Multiply.multiply(5, 2));
    }
}
