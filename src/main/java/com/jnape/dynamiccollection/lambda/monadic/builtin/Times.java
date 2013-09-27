package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import static com.jnape.dynamiccollection.lambda.dyadic.builtin.Multiply.multiply;

public final class Times extends MonadicFunction<Number, Number> {

    private final Number multiplier;

    public Times(Number multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public Number apply(Number multiplicand) {
        return multiply(multiplicand, multiplier);
    }

    public static MonadicFunction<Number, Number> times(Number multiplier) {
        return new Times(multiplier);
    }
}