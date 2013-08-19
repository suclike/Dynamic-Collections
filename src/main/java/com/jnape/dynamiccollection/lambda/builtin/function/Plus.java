package com.jnape.dynamiccollection.lambda.builtin.function;

import com.jnape.dynamiccollection.lambda.Function;

import static com.jnape.dynamiccollection.lambda.builtin.accumulator.Add.add;

public class Plus implements Function<Number, Number> {

    private final Number addend;

    public Plus(Number addend) {
        this.addend = addend;
    }

    @Override
    public Number apply(Number augend) {
        return add(augend, addend);
    }

    public static Function<Number, Number> plus(Number addend) {
        return new Plus(addend);
    }
}
