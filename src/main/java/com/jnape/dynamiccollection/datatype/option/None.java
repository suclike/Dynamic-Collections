package com.jnape.dynamiccollection.datatype.option;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import static com.jnape.dynamiccollection.datatype.option.Some.some;

public final class None<Value> extends Option<Value> {

    private static final None NONE = new None();

    private None() {
    }

    @Override
    public Value get() {
        throw new IllegalStateException("Can't get value from None.");
    }

    @Override
    public Option<Value> orElse(Value orElse) {
        return some(orElse);
    }

    @Override
    public <Output> Option<Output> map(MonadicFunction<? super Value, Output> mapper) {
        return none();
    }

    @Override
    public Value getOrElse(Value orElse) {
        return orElse;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof None;
    }

    @Override
    public String toString() {
        return "None()";
    }

    @SuppressWarnings("unchecked")
    public static <Value> None<Value> none() {
        return (None<Value>) NONE;
    }
}
