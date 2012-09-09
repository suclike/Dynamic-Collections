package com.jnape.dynamiccollection.operation;

import static java.lang.String.format;

public enum NumericType {

    BYTE(Byte.class),
    SHORT(Short.class),
    INTEGER(Integer.class),
    LONG(Long.class),
    FLOAT(Float.class),
    DOUBLE(Double.class);

    private final Class<? extends Number> nativeClass;

    private NumericType(Class<? extends Number> nativeClass) {
        this.nativeClass = nativeClass;
    }

    public Class<? extends Number> getNativeClass() {
        return nativeClass;
    }

    private boolean hasPrecedenceOver(NumericType numericType) {
        return ordinal() > numericType.ordinal();
    }

    public Number coerce(Number number) {
        switch (this) {
            case BYTE:
                return number.byteValue();
            case SHORT:
                return number.shortValue();
            case INTEGER:
                return number.intValue();
            case LONG:
                return number.longValue();
            case FLOAT:
                return number.floatValue();
            case DOUBLE:
                return number.doubleValue();
        }

        throw new Error("Can't get here");
    }

    public static NumericType coercionFor(Number number, Number... moreNumbers) {
        NumericType highestPrecedence = typeOf(number);

        for (Number anotherNumber : moreNumbers) {
            NumericType numericType = typeOf(anotherNumber);
            if (numericType.hasPrecedenceOver(highestPrecedence))
                highestPrecedence = numericType;
        }

        return highestPrecedence;
    }

    private static NumericType typeOf(Number number) {
        for (NumericType numericType : values())
            if (numericType.getNativeClass().isInstance(number))
                return numericType;

        throw new IllegalArgumentException(format("No NumericType found for native class <%s>", number.getClass()));
    }
}
