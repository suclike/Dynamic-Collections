package com.jnape.dynamiccollection.lambda.dyadic.builtin;

import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;

public final class Subtract extends Accumulator<Number, Number> {

    public static Number subtract(Number minuend, Number subtrahend) {
        return minus().apply(minuend, subtrahend);
    }

    public static Subtract minus() {
        return new Subtract();
    }

    @Override
    public Number apply(Number minuend, Number subtrahend) {
        double difference = minuend.doubleValue() - subtrahend.doubleValue();
        return coercionFor(minuend, subtrahend).coerce(difference);
    }
}
