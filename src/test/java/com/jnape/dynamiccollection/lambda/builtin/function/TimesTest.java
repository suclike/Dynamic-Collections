package com.jnape.dynamiccollection.lambda.builtin.function;

import com.jnape.dynamiccollection.lambda.Function;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimesTest {

    @Test
    public void shouldHaveStaticFactoryMethodForCurriedMultiplier() {
        Function<Number, Number> times2 = Times.times(2);
        assertEquals(10, times2.apply(5));
    }
}
