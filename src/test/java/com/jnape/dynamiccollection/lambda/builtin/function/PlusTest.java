package com.jnape.dynamiccollection.lambda.builtin.function;

import com.jnape.dynamiccollection.lambda.Function;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlusTest {

    @Test
    public void shouldHaveStaticFactoryMethodForCurriedAddend() {
        Function<Number, Number> plus10 = Plus.plus(10);
        assertEquals(15, plus10.apply(5));
    }
}
