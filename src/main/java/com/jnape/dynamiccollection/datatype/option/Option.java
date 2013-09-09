package com.jnape.dynamiccollection.datatype.option;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import static com.jnape.dynamiccollection.datatype.option.Some.some;

public abstract class Option<Value> {

    public final Boolean isNone() {
        return this instanceof None;
    }

    public final Boolean isSome() {
        return !isNone();
    }

    public abstract Value get();

    public abstract Option<Value> orElse(Value value);

    public abstract <Output> Option<Output> map(MonadicFunction<? super Value, Output> mapper);

    public abstract Value getOrElse(Value orElse);

    @SuppressWarnings("FinalStaticMethod")
    public static final <Value> Option<Value> option(Value value) {
        return value != null ? some(value) : None.<Value>none();
    }
}
