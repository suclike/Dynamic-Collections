package com.jnape.dynamiccollection.datatype.option;

import com.jnape.dynamiccollection.lambda.Function;

public interface Option<Value> {

    Value get();

    Option<Value> orElse(Value value);

    <Output> Option<Output> map(Function<? super Value, Output> mapper);

    Value getOrElse(Value orElse);
}
