package com.jnape.dynamiccollection.lambda;

public interface Function<Input, Output> {
    Output apply(Input input);
}