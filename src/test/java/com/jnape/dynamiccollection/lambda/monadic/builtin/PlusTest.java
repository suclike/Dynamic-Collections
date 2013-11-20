package com.jnape.dynamiccollection.lambda.monadic.builtin;

import org.junit.Test;

import static com.jnape.dynamiccollection.lambda.monadic.builtin.Plus.plus;
import static org.junit.Assert.assertEquals;

public class PlusTest {

    @Test
    public void shouldHaveStaticFactoryMethodForCurriedAddend() {
        Plus<Integer> plus10 = plus(10);
        assertEquals((Integer) 15, plus10.apply(5));
    }
}
