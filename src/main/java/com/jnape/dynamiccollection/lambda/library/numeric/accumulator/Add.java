package com.jnape.dynamiccollection.lambda.library.numeric.accumulator;

import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.operation.NumericType;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;
import static java.lang.String.format;

public class Add implements Accumulator<Number, Number> {

    @Override
    public Number apply(Number augend, Number addend) {
        NumericType coercedType = coercionFor(augend, addend);

        switch (coercedType) {
            case BYTE:
                return augend.byteValue() + addend.byteValue();
            case SHORT:
                return augend.shortValue() + addend.shortValue();
            case INTEGER:
                return augend.intValue() + addend.intValue();
            case LONG:
                return augend.longValue() + addend.longValue();
            case FLOAT:
                return augend.floatValue() + addend.floatValue();
            case DOUBLE:
                return augend.doubleValue() + addend.doubleValue();
        }

        throw new IllegalArgumentException(format("No addition strategy for <%s>", coercedType));
    }

    public static Number add(Number augend, Number addend) {
        return plus().apply(augend, addend);
    }

    public static Add plus() {
        return new Add();
    }
}
