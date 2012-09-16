package com.jnape.dynamiccollection.lambda.library.numeric.accumulator;

import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.operation.NumericType;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;
import static java.lang.String.format;

public class Divide implements Accumulator<Number, Number> {

    @Override
    public Number apply(Number dividend, Number divisor) {
        NumericType coercedType = coercionFor(dividend, divisor);

        switch (coercedType) {
            case BYTE:
                return dividend.byteValue() / divisor.byteValue();
            case SHORT:
                return dividend.shortValue() / divisor.shortValue();
            case INTEGER:
                return dividend.intValue() / divisor.intValue();
            case LONG:
                return dividend.longValue() / divisor.longValue();
            case FLOAT:
                return dividend.floatValue() / divisor.floatValue();
            case DOUBLE:
                return dividend.doubleValue() / divisor.doubleValue();
        }

        throw new IllegalArgumentException(format("No division strategy for <%s>", coercedType));
    }

    public static Number divide(Number dividend, Number divisor) {
        return divided_by().apply(dividend, divisor);
    }

    public static Function<Number, Number> divided_by(final Number divisor) {
        return new Function<Number, Number>() {
            @Override
            public Number apply(Number dividend) {
                return divide(dividend, divisor);
            }
        };
    }

    public static Divide divided_by() {
        return new Divide();
    }
}
