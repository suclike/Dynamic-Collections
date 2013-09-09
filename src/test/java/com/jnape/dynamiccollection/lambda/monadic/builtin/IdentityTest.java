package com.jnape.dynamiccollection.lambda.monadic.builtin;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class IdentityTest {

    @Test
    public void shouldReturnBackInput() {
        Identity<Integer> identity = new Identity<Integer>();
        assertEquals((Integer) 10, identity.apply(10));
    }

    @Test
    public void shouldHaveStaticFactoryMethod() {
        assertReflectionEquals(new Identity(), Identity.id());
    }
}
