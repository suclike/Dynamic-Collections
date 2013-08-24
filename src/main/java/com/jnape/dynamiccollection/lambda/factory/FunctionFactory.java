package com.jnape.dynamiccollection.lambda.factory;

import com.jnape.dynamiccollection.lambda.MonadicFunction;

public class FunctionFactory {

    public static <ConstantValue> MonadicFunction<Object, ConstantValue> always(final ConstantValue constantValue) {
        return new MonadicFunction<Object, ConstantValue>() {
            @Override
            public ConstantValue apply(Object anything) {
                return constantValue;
            }
        };
    }
}
