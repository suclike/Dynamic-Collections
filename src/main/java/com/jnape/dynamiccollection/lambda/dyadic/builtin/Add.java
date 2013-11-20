package com.jnape.dynamiccollection.lambda.dyadic.builtin;

import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;

public final class Add<Numeric extends Number> extends Accumulator<Numeric, Numeric> {

    public static <Numeric extends Number> Numeric add(Numeric augend, Numeric addend) {
        return Add.<Numeric>plus().apply(augend, addend);
    }

    public static <Numeric extends Number> Add<Numeric> plus() {
        return new Add<Numeric>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Numeric apply(Numeric augend, Numeric addend) {
        double sum = augend.doubleValue() + addend.doubleValue();
        return (Numeric) coercionFor(augend, addend).coerce(sum);
    }
}
