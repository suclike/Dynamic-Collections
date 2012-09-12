package com.jnape.dynamiccollection.lambda.library.numeric.function.map;

import com.jnape.dynamiccollection.lambda.Function;

import static com.jnape.dynamiccollection.lambda.library.numeric.accumulator.Multiply.multiply;

public class Square implements Function<Number, Number> {

    @Override
    public Number apply(Number number) {
        return multiply(number, number);
    }

    public static Square square() {
        return new Square();
    }
}
