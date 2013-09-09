package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlusTest {

    @Test
    public void shouldHaveStaticFactoryMethodForCurriedAddend() {
        MonadicFunction<Number, Number> plus10 = Plus.plus(10);
        assertEquals(15, plus10.apply(5));
    }
}
