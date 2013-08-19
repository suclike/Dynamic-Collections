package com.jnape.dynamiccollection.lambda.builtin.function;

import com.jnape.dynamiccollection.lambda.Function;

import static com.jnape.dynamiccollection.lambda.builtin.accumulator.Subtract.subtract;

public class Minus implements Function<Number, Number> {

    private final Number subtrahend;

    public Minus(Number subtrahend) {
        this.subtrahend = subtrahend;
    }

    @Override
    public Number apply(Number minuend) {
        return subtract(minuend, subtrahend);
    }

    public static Function<Number, Number> minus(Number subtrahend) {
        return new Minus(subtrahend);
    }
}
