package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import static com.jnape.dynamiccollection.lambda.dyadic.builtin.Subtract.subtract;

public class Minus extends MonadicFunction<Number, Number> {

    private final Number subtrahend;

    public Minus(Number subtrahend) {
        this.subtrahend = subtrahend;
    }

    @Override
    public Number apply(Number minuend) {
        return subtract(minuend, subtrahend);
    }

    public static MonadicFunction<Number, Number> minus(Number subtrahend) {
        return new Minus(subtrahend);
    }
}
