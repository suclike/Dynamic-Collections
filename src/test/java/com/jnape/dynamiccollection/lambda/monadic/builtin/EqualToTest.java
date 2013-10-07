package com.jnape.dynamiccollection.lambda.monadic.builtin;

import org.junit.Before;
import org.junit.Test;

import static com.jnape.dynamiccollection.lambda.monadic.builtin.EqualTo.eq;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class EqualToTest {

    private EqualTo<Integer> equalTo;

    @Before
    public void setUp() {
        equalTo = new EqualTo<Integer>(4);
    }

    @Test
    public void shouldHaveStaticFactoryMethod() {
        assertReflectionEquals(new EqualTo<String>("foo"), eq("foo"));
    }

    @Test
    public void shouldBeTrueIfLeftHandEqualsRightHand() {
        assertTrue(equalTo.apply(4));
    }

    @Test
    public void shouldBeFalseIfLeftHandDoesNotEqualRightHand() {
        assertFalse(equalTo.apply(10));
    }
}
