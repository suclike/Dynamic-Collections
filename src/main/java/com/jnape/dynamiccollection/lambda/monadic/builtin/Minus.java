package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import static com.jnape.dynamiccollection.lambda.dyadic.builtin.Subtract.subtract;

public class Minus<Numeric extends Number> extends MonadicFunction<Numeric, Numeric> {

    private final Numeric subtrahend;

    public Minus(Numeric subtrahend) {
        this.subtrahend = subtrahend;
    }

    @Override
    public Numeric apply(Numeric minuend) {
        return subtract(minuend, subtrahend);
    }

    public static <Numeric extends Number> Minus<Numeric> minus(Numeric subtrahend) {
        return new Minus<Numeric>(subtrahend);
    }
}
