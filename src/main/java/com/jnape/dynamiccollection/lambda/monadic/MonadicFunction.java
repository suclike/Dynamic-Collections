package com.jnape.dynamiccollection.lambda.monadic;

public abstract class MonadicFunction<Input, Output> {
    public abstract Output apply(Input input);
}
