package com.jnape.dynamiccollection.lambda.builtin.accumulator;

import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;

public final class Add extends Accumulator<Number, Number> {

    public static Number add(Number augend, Number addend) {
        return plus().apply(augend, addend);
    }

    public static Add plus() {
        return new Add();
    }

    @Override
    public Number apply(Number augend, Number addend) {
        double sum = augend.doubleValue() + addend.doubleValue();
        return coercionFor(augend, addend).coerce(sum);
    }
}
