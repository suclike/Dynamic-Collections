package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import static com.jnape.dynamiccollection.lambda.dyadic.builtin.Multiply.multiply;

public final class Times<Numeric extends Number> extends MonadicFunction<Numeric, Numeric> {

    private final Numeric multiplier;

    public Times(Numeric multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public Numeric apply(Numeric multiplicand) {
        return multiply(multiplicand, multiplier);
    }

    public static <Numeric extends Number> Times<Numeric> times(Numeric multiplier) {
        return new Times<Numeric>(multiplier);
    }
}
