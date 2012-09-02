package com.jnape.dynamiccollection.operation;

import org.junit.Test;

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
    public void shouldCoerceNumbersUpToNumberWithHighestPrecedence() {
        assertEquals(SHORT, coercionFor((byte) 1, (short) 2));
        assertEquals(INTEGER, coercionFor((byte) 1, (short) 2, 3));
        assertEquals(LONG, coercionFor((byte) 1, (short) 2, 3, 4L));
        assertEquals(FLOAT, coercionFor((byte) 1, (short) 2, 3, 4L, 5F));
        assertEquals(DOUBLE, coercionFor((byte) 1, (short) 2, 3, 4L, 5F, 6D));
    }
}
