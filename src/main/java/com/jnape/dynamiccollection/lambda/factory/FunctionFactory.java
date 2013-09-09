package com.jnape.dynamiccollection.lambda.factory;

import com.jnape.dynamiccollection.lambda.NiladicFunction;

public class FunctionFactory {

    public static <ConstantValue> NiladicFunction<ConstantValue> always(final ConstantValue constantValue) {
        return new NiladicFunction<ConstantValue>() {
            @Override
            public ConstantValue apply() {
                return constantValue;
            }
        };
    }
}
