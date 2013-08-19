package com.jnape.dynamiccollection.lambda.builtin.function;

import com.jnape.dynamiccollection.lambda.Function;

import static com.jnape.dynamiccollection.lambda.builtin.accumulator.Divide.divide;

public class DividedBy implements Function<Number, Number> {

    private final Number divisor;

    public DividedBy(Number divisor) {
        this.divisor = divisor;
    }

    @Override
    public Number apply(Number dividend) {
        return divide(dividend, divisor);
    }

    public static Function<Number, Number> divided_by(Number divisor) {
        return new DividedBy(divisor);
    }
}
