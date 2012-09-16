package com.jnape.dynamiccollection.lambda.library.numeric.accumulator;

import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.operation.NumericType;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;
import static java.lang.String.format;

public class Subtract implements Accumulator<Number, Number> {

    @Override
    public Number apply(Number minuend, Number subtrahend) {
        NumericType coercedType = coercionFor(minuend, subtrahend);

        switch (coercedType) {
            case BYTE:
                return minuend.byteValue() - subtrahend.byteValue();
            case SHORT:
                return minuend.shortValue() - subtrahend.shortValue();
            case INTEGER:
                return minuend.intValue() - subtrahend.intValue();
            case LONG:
                return minuend.longValue() - subtrahend.longValue();
            case FLOAT:
                return minuend.floatValue() - subtrahend.floatValue();
            case DOUBLE:
                return minuend.doubleValue() - subtrahend.doubleValue();
        }

        throw new IllegalArgumentException(format("No subtraction strategy for <%s>", coercedType));
    }

    public static Number subtract(Number minuend, Number subtrahend) {
        return minus().apply(minuend, subtrahend);
    }

    public static Function<Number, Number> minus(final Number subtrahend) {
        return new Function<Number, Number>() {
            @Override
            public Number apply(Number minuend) {
                return subtract(minuend, subtrahend);
            }
        };
    }

    public static Subtract minus() {
        return new Subtract();
    }
}
