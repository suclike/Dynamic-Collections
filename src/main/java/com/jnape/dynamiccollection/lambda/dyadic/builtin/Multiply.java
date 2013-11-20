package com.jnape.dynamiccollection.lambda.dyadic.builtin;

import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;

public final class Multiply<Numeric extends Number> extends Accumulator<Numeric, Numeric> {

    public static <Numeric extends Number> Numeric multiply(Numeric multiplicand, Numeric multiplier) {
        return Multiply.<Numeric>times().apply(multiplicand, multiplier);
    }

    public static <Numeric extends Number> Multiply<Numeric> times() {
        return new Multiply<Numeric>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Numeric apply(Numeric multiplicand, Numeric multiplier) {
        double product = multiplicand.doubleValue() * multiplier.doubleValue();
        return (Numeric) coercionFor(multiplicand, multiplier).coerce(product);
    }
}
