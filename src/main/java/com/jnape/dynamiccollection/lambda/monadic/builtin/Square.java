package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import static com.jnape.dynamiccollection.lambda.dyadic.builtin.Multiply.multiply;

public final class Square<Numeric extends Number> extends MonadicFunction<Numeric, Numeric> {

    @Override
    public Numeric apply(Numeric number) {
        return multiply(number, number);
    }

    public static <Numeric extends Number> Square<Numeric> square() {
        return new Square<Numeric>();
    }
}
