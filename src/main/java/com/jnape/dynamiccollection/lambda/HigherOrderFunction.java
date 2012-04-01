package com.jnape.dynamiccollection.lambda;

public interface HigherOrderFunction<Input, Output> {
    Output apply(Input input, Output accumulation);
}
