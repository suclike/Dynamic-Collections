package com.jnape.dynamiccollection.lambda.builtin.function;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinusTest {

    @Test
    public void shouldHaveStaticFactoryMethodForCurriedSubtrahend() {
        MonadicFunction<Number, Number> minus10 = Minus.minus(10);
        assertEquals(5, minus10.apply(15));
    }
}
