package com.jnape.dynamiccollection.lambda;

public abstract class NiladicFunction<Output> extends MonadicFunction<Object, Output> {

    @Override
    public final Output apply(Object discarded) {
        return apply();
    }

    public abstract Output apply();
}
