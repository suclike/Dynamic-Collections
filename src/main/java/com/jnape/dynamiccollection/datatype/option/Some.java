package com.jnape.dynamiccollection.datatype.option;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import static java.lang.String.format;

public final class Some<Value> extends Option<Value> {

    private final Value value;

    public Some(Value value) {
        this.value = value;
    }

    @Override
    public Value get() {
        return value;
    }

    @Override
    public Option<Value> orElse(Value value) {
        return this;
    }

    @Override
    public <Output> Option<Output> map(MonadicFunction<? super Value, Output> mapper) {
        return some(mapper.apply(value));
    }

    @Override
    public Value getOrElse(Value orElse) {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Some) {
            Some that = (Some) other;
            return this.value.equals(that.value);
        }
        return false;
    }

    @Override
    public String toString() {
        return format("Some[%s]", value);
    }

    public static <Value> Some<Value> some(Value value) {
        return new Some<Value>(value);
    }
}
