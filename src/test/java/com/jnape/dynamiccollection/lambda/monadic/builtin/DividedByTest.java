package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DividedByTest {

    @Test
    public void shouldHaveStaticFactoryMethodForCurriedDivisor() {
        MonadicFunction<Number, Number> dividedBy2 = DividedBy.divided_by(2);
        assertEquals(5, dividedBy2.apply(10));
    }
}