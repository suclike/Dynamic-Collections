package com.jnape.dynamiccollection.lambda.dyadic.builtin;

import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;

public final class Divide<Numeric extends Number> extends Accumulator<Numeric, Numeric> {

    public static <Numeric extends Number> Numeric divide(Numeric dividend, Numeric divisor) {
        return Divide.<Numeric>dividedBy().apply(dividend, divisor);
    }

    public static <Numeric extends Number> Divide<Numeric> dividedBy() {
        return new Divide<Numeric>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Numeric apply(Numeric dividend, Numeric divisor) {
        double quotient = dividend.doubleValue() / divisor.doubleValue();
        return (Numeric) coercionFor(dividend, divisor).coerce(quotient);
    }
}
