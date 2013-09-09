package com.jnape.dynamiccollection.datatype.option;

import com.jnape.dynamiccollection.lambda.MonadicFunction;

public class None<Value> implements Option<Value> {

    @Override
    public Value get() {
        throw new IllegalStateException("Can't get value from None.");
    }

    @Override
    public Option<Value> orElse(Value orElse) {
        return new Some<Value>(orElse);
    }

    @Override
    public <Output> Option<Output> map(MonadicFunction<? super Value, Output> mapper) {
        return new None<Output>();
    }

    @Override
    public Value getOrElse(Value orElse) {
        return orElse;
    }

    @Override
    public Boolean isNone() {
        return true;
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
        return "None[]";
    }
}
