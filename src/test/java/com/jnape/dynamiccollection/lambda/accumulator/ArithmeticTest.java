package com.jnape.dynamiccollection.lambda.accumulator;

import org.junit.Test;
import testsupport.UnsupportedNumber;

import static com.jnape.dynamiccollection.lambda.accumulator.Arithmetic.*;
import static org.junit.Assert.assertEquals;

public class ArithmeticTest {

    @Test
    public void shouldAddNumbers() {
        assertEquals(1, PLUS.apply((byte) 0, (byte) 1));
        assertEquals(3, PLUS.apply((short) 1, (short) 2));
        assertEquals(5, PLUS.apply(2, 3));
        assertEquals(7l, PLUS.apply(3l, 4l));
        assertEquals(9f, PLUS.apply(4f, 5f));
        assertEquals(11d, PLUS.apply(5d, 6d));
    }

    @Test
    public void shouldSubtractNumbers() {
        assertEquals(1, MINUS.apply((byte) 1, (byte) 0));
        assertEquals(3, MINUS.apply((short) 4, (short) 1));
        assertEquals(5, MINUS.apply(7, 2));
        assertEquals(7l, MINUS.apply(10l, 3l));
        assertEquals(9f, MINUS.apply(13f, 4f));
        assertEquals(11d, MINUS.apply(16d, 5d));
    }

    @Test
    public void shouldMultiplyNumbers() {
        assertEquals(96, TIMES.apply((byte) 1, (byte) 96));
        assertEquals(96, TIMES.apply((short) 2, (short) 48));
        assertEquals(96, TIMES.apply(3, 32));
        assertEquals(96l, TIMES.apply(4l, 24l));
        assertEquals(96f, TIMES.apply(6f, 16f));
        assertEquals(96d, TIMES.apply(8d, 12d));
    }

    @Test
    public void shouldDivideNumbers() {
        assertEquals(96, DIVIDED_BY.apply((byte) 96, (byte) 1));
        assertEquals(48, DIVIDED_BY.apply((short) 96, (short) 2));
        assertEquals(32, DIVIDED_BY.apply(96, 3));
        assertEquals(24l, DIVIDED_BY.apply(96l, 4l));
        assertEquals(16f, DIVIDED_BY.apply(96f, 6f));
        assertEquals(12d, DIVIDED_BY.apply(96d, 8d));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfAddingUnsupportedNumericType() {
        UnsupportedNumber unsupportedNumber = new UnsupportedNumber();
        PLUS.apply(1, unsupportedNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfSubtractingUnsupportedNumericType() {
        UnsupportedNumber unsupportedNumber = new UnsupportedNumber();
        MINUS.apply(1, unsupportedNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfMultiplyingUnsupportedNumericType() {
        UnsupportedNumber unsupportedNumber = new UnsupportedNumber();
        TIMES.apply(1, unsupportedNumber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfDividingUnsupportedNumericType() {
        UnsupportedNumber unsupportedNumber = new UnsupportedNumber();
        DIVIDED_BY.apply(1, unsupportedNumber);
    }
}
