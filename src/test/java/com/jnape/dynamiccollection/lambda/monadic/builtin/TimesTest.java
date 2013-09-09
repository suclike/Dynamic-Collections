package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimesTest {

    @Test
    public void shouldHaveStaticFactoryMethodForCurriedMultiplier() {
        MonadicFunction<Number, Number> times2 = Times.times(2);
        assertEquals(10, times2.apply(5));
    }
}
