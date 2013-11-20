package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import static com.jnape.dynamiccollection.lambda.dyadic.builtin.Divide.divide;

public final class DivideBy<Numeric extends Number> extends MonadicFunction<Numeric, Numeric> {

    private final Numeric divisor;

    public DivideBy(Numeric divisor) {
        this.divisor = divisor;
    }

    @Override
    public Numeric apply(Numeric dividend) {
        return divide(dividend, divisor);
    }

    public static <Numeric extends Number> DivideBy<Numeric> divideBy(Numeric divisor) {
        return new DivideBy<Numeric>(divisor);
    }
}
