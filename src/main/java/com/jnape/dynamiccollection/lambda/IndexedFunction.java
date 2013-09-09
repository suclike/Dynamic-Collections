package com.jnape.dynamiccollection.lambda;

public abstract class IndexedFunction<Input, Output> extends DyadicFunction<Number, Input, Output> {

    @Override
    public abstract Output apply(Number index, Input input);
}
