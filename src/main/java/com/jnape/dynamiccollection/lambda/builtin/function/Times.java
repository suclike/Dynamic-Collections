package com.jnape.dynamiccollection.lambda.builtin.function;

import com.jnape.dynamiccollection.lambda.Function;

import static com.jnape.dynamiccollection.lambda.builtin.accumulator.Multiply.multiply;

public class Times implements Function<Number, Number> {

    private final Number multiplier;

    public Times(Number multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public Number apply(Number multiplicand) {
        return multiply(multiplicand, multiplier);
    }

    public static Function<Number, Number> times(Number multiplier) {
        return new Times(multiplier);
    }
}
