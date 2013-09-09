package com.jnape.dynamiccollection.lambda.dyadic.builtin;

import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;

public final class Multiply extends Accumulator<Number, Number> {

    public static Number multiply(Number multiplicand, Number multiplier) {
        return times().apply(multiplicand, multiplier);
    }

    public static Multiply times() {
        return new Multiply();
    }

    @Override
    public Number apply(Number multiplicand, Number multiplier) {
        double product = multiplicand.doubleValue() * multiplier.doubleValue();
        return coercionFor(multiplicand, multiplier).coerce(product);
    }
}
