package com.jnape.dynamiccollection.lambda.niladic.builtin;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class AlwaysTest {

    @Test
    public void shouldAlwaysReturnTheSameValue() {
        Object anything = new Object();
        Always<Integer> always3 = new Always<Integer>(3);

        assertEquals((Integer) 3, always3.apply(anything));
    }

    @Test
    public void shouldHaveStaticFactoryMethod() {
        assertReflectionEquals(new Always<Integer>(3), Always.always(3));
    }
}
