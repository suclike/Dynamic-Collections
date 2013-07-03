package com.jnape.dynamiccollection.lambda.library.numeric.accumulator;

import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.Function;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;

public class Divide implements Accumulator<Number, Number> {

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

    @Override
    public Number apply(Number dividend, Number divisor) {
        double quotient = dividend.doubleValue() / divisor.doubleValue();
        return coercionFor(dividend, divisor).coerce(quotient);
    }
}
