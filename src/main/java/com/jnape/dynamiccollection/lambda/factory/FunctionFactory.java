package com.jnape.dynamiccollection.lambda.factory;

import com.jnape.dynamiccollection.lambda.Function;

public class FunctionFactory {

    public static <ConstantValue> Function<Object, ConstantValue> always(final ConstantValue constantValue) {
        return new Function<Object, ConstantValue>() {
            @Override
            public ConstantValue apply(Object anything) {
                return constantValue;
            }
        };
    }
}
