package com.jnape.dynamiccollection.operation;

import org.junit.Test;
import testsupport.UnsupportedNumber;

import static com.jnape.dynamiccollection.operation.NumericType.*;
import static org.junit.Assert.assertEquals;

public class NumericTypeTest {

    @Test
    public void shouldKnowCoercionForHomogeneousNumbersIsSameType() {
        assertEquals(BYTE, coercionFor((byte) 1, (byte) 2));
        assertEquals(SHORT, coercionFor((short) 1, (short) 2));
        assertEquals(INTEGER, coercionFor(1, 2));
        assertEquals(LONG, coercionFor(1L, 2L));
        assertEquals(FLOAT, coercionFor(1F, 2F));
        assertEquals(DOUBLE, coercionFor(1D, 2D));
    }

    @Test
    public void shouldKnowCoercionForHeterogeneousNumbersIsBasedOnCompilerPrecedence() {
        assertEquals(SHORT, coercionFor((byte) 1, (short) 2));
        assertEquals(INTEGER, coercionFor((byte) 1, (short) 2, 3));
        assertEquals(LONG, coercionFor((byte) 1, (short) 2, 3, 4L));
        assertEquals(FLOAT, coercionFor((byte) 1, (short) 2, 3, 4L, 5F));
        assertEquals(DOUBLE, coercionFor((byte) 1, (short) 2, 3, 4L, 5F, 6D));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfGettingCoercionForUnsupportedNumber() {
        coercionFor(new UnsupportedNumber());
    }

    @Test
    public void shouldCoerceNumberIntoNativeClassRepresentedByNumericType() {
        assertEquals((byte) 1, BYTE.coerce(1));
        assertEquals((short) 1, SHORT.coerce(1D));
        assertEquals(1, INTEGER.coerce(1L));
        assertEquals(1L, LONG.coerce((byte) 1));
        assertEquals(1F, FLOAT.coerce((short) 1));
        assertEquals(1D, DOUBLE.coerce(1F));
    }
}
