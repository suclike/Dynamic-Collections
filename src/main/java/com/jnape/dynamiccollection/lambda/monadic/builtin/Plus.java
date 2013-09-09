package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import static com.jnape.dynamiccollection.lambda.dyadic.builtin.Add.add;

public final class Plus extends MonadicFunction<Number, Number> {

    private final Number addend;

    public Plus(Number addend) {
        this.addend = addend;
    }

    @Override
    public Number apply(Number augend) {
        return add(augend, addend);
    }

    public static MonadicFunction<Number, Number> plus(Number addend) {
        return new Plus(addend);
    }
}
