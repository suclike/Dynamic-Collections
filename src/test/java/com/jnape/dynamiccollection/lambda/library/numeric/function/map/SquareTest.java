package com.jnape.dynamiccollection.lambda.library.numeric.function.map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class SquareTest {

    @Test
    public void shouldSquareNumber() {
        Square square = new Square();

        assertEquals(1, square.apply((byte) 1));
        assertEquals(4, square.apply((short) 2));
        assertEquals(9, square.apply(3));
        assertEquals(16l, square.apply(4l));
        assertEquals(25f, square.apply(5f));
        assertEquals(36d, square.apply(6d));
    }

    @Test
    public void shouldHaveStaticFactoryMethod() {
        assertReflectionEquals(new Square(), Square.square());
    }
}
