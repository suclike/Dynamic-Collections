package com.jnape.dynamiccollection.lambda.dyadic;

public abstract class IndexedFunction<Input, Output> extends DyadicFunction<Number, Input, Output> {

    @Override
    public abstract Output apply(Number index, Input input);
}
