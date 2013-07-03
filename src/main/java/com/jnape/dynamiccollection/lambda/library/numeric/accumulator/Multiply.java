package com.jnape.dynamiccollection.lambda.library.numeric.accumulator;

import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.Function;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;

public class Multiply implements Accumulator<Number, Number> {

    public static Number multiply(Number multiplicand, Number multiplier) {
        return times().apply(multiplicand, multiplier);
    }

    public static Function<Number, Number> times(final Number multiplier) {
        return new Function<Number, Number>() {
            @Override
            public Number apply(Number multiplicand) {
                return multiply(multiplicand, multiplier);
            }
        };
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
