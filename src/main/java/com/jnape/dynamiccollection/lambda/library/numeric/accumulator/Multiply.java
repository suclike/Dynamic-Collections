package com.jnape.dynamiccollection.lambda.library.numeric.accumulator;

import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.operation.NumericType;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;
import static java.lang.String.format;

public class Multiply implements Accumulator<Number, Number> {

    @Override
    public Number apply(Number multiplicand, Number multiplier) {
        NumericType coercedType = coercionFor(multiplicand, multiplier);

        switch (coercedType) {
            case BYTE:
                return multiplicand.byteValue() * multiplier.byteValue();
            case SHORT:
                return multiplicand.shortValue() * multiplier.shortValue();
            case INTEGER:
                return multiplicand.intValue() * multiplier.intValue();
            case LONG:
                return multiplicand.longValue() * multiplier.longValue();
            case FLOAT:
                return multiplicand.floatValue() * multiplier.floatValue();
            case DOUBLE:
                return multiplicand.doubleValue() * multiplier.doubleValue();
        }

        throw new IllegalArgumentException(format("No multiplication strategy for <%s>", coercedType));
    }

    public static Number multiply(Number multiplicand, Number multiplier) {
        return times().apply(multiplicand, multiplier);
    }

    public static Multiply times() {
        return new Multiply();
    }
}
