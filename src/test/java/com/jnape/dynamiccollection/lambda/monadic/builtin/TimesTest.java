package com.jnape.dynamiccollection.lambda.monadic.builtin;

import org.junit.Test;

import static com.jnape.dynamiccollection.lambda.monadic.builtin.Times.times;
import static org.junit.Assert.assertEquals;

public class TimesTest {

    @Test
    public void shouldHaveStaticFactoryMethodForCurriedMultiplier() {
        Times<Integer> times2 = times(2);
        assertEquals((Integer) 10, times2.apply(5));
    }
}
