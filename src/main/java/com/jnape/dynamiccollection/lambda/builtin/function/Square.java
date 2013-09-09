package com.jnape.dynamiccollection.lambda.builtin.function;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import static com.jnape.dynamiccollection.lambda.builtin.accumulator.Multiply.multiply;

public final class Square extends MonadicFunction<Number, Number> {

    @Override
    public Number apply(Number number) {
        return multiply(number, number);
    }

    public static Square square() {
        return new Square();
    }
}
