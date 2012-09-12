package com.jnape.dynamiccollection.lambda.library.numeric.accumulator;

import org.junit.Test;
import testsupport.UnsupportedNumber;

import static org.junit.Assert.assertEquals;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class AddTest {

    @Test
    public void shouldAddNumbers() {
        Add add = new Add();

        assertEquals(1, add.apply((byte) 0, (byte) 1));
        assertEquals(3, add.apply((short) 1, (short) 2));
        assertEquals(5, add.apply(2, 3));
        assertEquals(7l, add.apply(3l, 4l));
        assertEquals(9f, add.apply(4f, 5f));
        assertEquals(11d, add.apply(5d, 6d));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfAddingUnsupportedNumericType() {
        UnsupportedNumber unsupportedNumber = new UnsupportedNumber();
        new Add().apply(1, unsupportedNumber);
    }

    @Test
    public void shouldHaveStaticFactoryMethod() {
        assertReflectionEquals(new Add(), Add.plus());
    }

    @Test
    public void shouldProvideConvenienceMethodForApply() {
        assertEquals(3, Add.add(1, 2));
    }
}
