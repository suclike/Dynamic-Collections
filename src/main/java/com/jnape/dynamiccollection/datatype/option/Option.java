package com.jnape.dynamiccollection.datatype.option;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

public interface Option<Value> {

    Value get();

    Option<Value> orElse(Value value);

    <Output> Option<Output> map(MonadicFunction<? super Value, Output> mapper);

    Value getOrElse(Value orElse);

    Boolean isNone();
}
