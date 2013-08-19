package com.jnape.dynamiccollection.lambda.builtin.accumulator;

import com.jnape.dynamiccollection.lambda.Accumulator;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;

public class Divide implements Accumulator<Number, Number> {

    public static Number divide(Number dividend, Number divisor) {
        return divided_by().apply(dividend, divisor);
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
