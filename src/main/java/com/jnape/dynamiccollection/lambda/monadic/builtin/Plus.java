package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import static com.jnape.dynamiccollection.lambda.dyadic.builtin.Add.add;

public final class Plus<Numeric extends Number> extends MonadicFunction<Numeric, Numeric> {

    private final Numeric addend;

    public Plus(Numeric addend) {
        this.addend = addend;
    }

    @Override
    public Numeric apply(Numeric augend) {
        return add(augend, addend);
    }

    public static <Numeric extends Number> Plus<Numeric> plus(Numeric addend) {
        return new Plus<Numeric>(addend);
    }
}
