package com.jnape.dynamiccollection.lambda.library.numeric.accumulator;

import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.Function;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;

public class Subtract implements Accumulator<Number, Number> {

    public static Number subtract(Number minuend, Number subtrahend) {
        return minus().apply(minuend, subtrahend);
    }

    public static Function<Number, Number> minus(final Number subtrahend) {
        return new Function<Number, Number>() {
            @Override
            public Number apply(Number minuend) {
                return subtract(minuend, subtrahend);
            }
        };
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
