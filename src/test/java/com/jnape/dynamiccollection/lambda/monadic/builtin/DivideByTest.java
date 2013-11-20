package com.jnape.dynamiccollection.lambda.monadic.builtin;

import org.junit.Test;

import static com.jnape.dynamiccollection.lambda.monadic.builtin.DivideBy.divideBy;
import static org.junit.Assert.assertEquals;

public class DivideByTest {

    @Test
    public void shouldHaveStaticFactoryMethodForCurriedDivisor() {
        DivideBy<Integer> dividedBy2 = divideBy(2);
        assertEquals((Integer) 5, dividedBy2.apply(10));
    }
}
