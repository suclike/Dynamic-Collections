package com.jnape.dynamiccollection.lambda.factory;

import com.jnape.dynamiccollection.lambda.MonadicFunction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionFactoryTest {

    @Test
    public void shouldCreateFunctionThatAlwaysReturnsTheSameValue() {
        Object anything = null;
        MonadicFunction<Object, Integer> always3 = FunctionFactory.always(3);

        assertEquals((Integer) 3, always3.apply(anything));
    }
}
