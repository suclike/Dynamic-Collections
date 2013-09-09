package com.jnape.dynamiccollection.lambda.niladic.builtin;

import com.jnape.dynamiccollection.lambda.niladic.NiladicFunction;

public class Always<ConstantValue> extends NiladicFunction<ConstantValue> {

    private final ConstantValue constantValue;

    public Always(ConstantValue constantValue) {
        this.constantValue = constantValue;
    }

    @Override
    public ConstantValue apply() {
        return constantValue;
    }

    public static <ConstantValue> Always<ConstantValue> always(ConstantValue constantValue) {
        return new Always<ConstantValue>(constantValue);
    }
}
