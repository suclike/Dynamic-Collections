package com.jnape.dynamiccollection.lambda.accumulator;

import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.operation.NumericType;

import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;
import static java.lang.String.format;

public final class Arithmetic {

    public static final Accumulator<Number, Number> PLUS = new Accumulator<Number, Number>() {
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
    };

    public static final Accumulator<Number, Number> MINUS = new Accumulator<Number, Number>() {
        @Override
        public final Number apply(Number minuend, Number subtrahend) {
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
    };

    public static final Accumulator<Number, Number> TIMES = new Accumulator<Number, Number>() {
        @Override
        public final Number apply(Number multiplicand, Number multiplier) {
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
    };

    public static final Accumulator<Number, Number> DIVIDED_BY = new Accumulator<Number, Number>() {
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

    };
}