package com.jnape.dynamiccollection.lambda.library.numeric.function.filter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class LessThanOrEqualToTest {

    private LessThanOrEqualTo lessThanOrEqualTo;

    @Before
    public void setUp() {
        lessThanOrEqualTo = new LessThanOrEqualTo(10);
    }

    @Test
    public void shouldBeTrueIfLeftHandIsLessThanRightHand() {
        assertTrue(lessThanOrEqualTo.apply(5));
    }

    @Test
    public void shouldBeTrueIfLeftHandIsEqualToRightHand() {
        assertTrue(lessThanOrEqualTo.apply(10));
    }

    @Test
    public void shouldBeFalseIfLeftHandIsGreaterThanRightHand() {
        assertFalse(lessThanOrEqualTo.apply(11));
    }

    @Test
    public void shouldHaveStaticFactoryMethod() {
        assertReflectionEquals(new LessThanOrEqualTo(3), LessThanOrEqualTo.lessThanOrEqualTo(3));
    }
}
