package com.jnape.dynamiccollection.lambda.monadic.builtin;

import org.junit.Test;

import static com.jnape.dynamiccollection.lambda.monadic.builtin.Minus.minus;
import static org.junit.Assert.assertEquals;

public class MinusTest {

    @Test
    public void shouldHaveStaticFactoryMethodForCurriedSubtrahend() {
        Minus<Integer> minus10 = minus(10);
        assertEquals((Integer) 5, minus10.apply(15));
    }
}
