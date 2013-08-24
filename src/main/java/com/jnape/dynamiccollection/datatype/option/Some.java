package com.jnape.dynamiccollection.datatype.option;

import com.jnape.dynamiccollection.lambda.Function;

public class Some<Value> implements Option<Value> {

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
    public <Output> Option<Output> map(Function<? super Value, Output> mapper) {
        return new Some<Output>(mapper.apply(value));
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
}
