package com.jnape.dynamiccollection.lambda.dyadic.builtin;

import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;

public final class Subtract<Numeric extends Number> extends Accumulator<Numeric, Numeric> {

    public static <Numeric extends Number> Numeric subtract(Numeric minuend, Numeric subtrahend) {
        return Subtract.<Numeric>minus().apply(minuend, subtrahend);
    }

    public static <Numeric extends Number> Subtract<Numeric> minus() {
        return new Subtract<Numeric>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Numeric apply(Numeric minuend, Numeric subtrahend) {
        double difference = minuend.doubleValue() - subtrahend.doubleValue();
        return (Numeric) coercionFor(minuend, subtrahend).coerce(difference);
    }
}
