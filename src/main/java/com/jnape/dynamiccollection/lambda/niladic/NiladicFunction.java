package com.jnape.dynamiccollection.lambda.niladic;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

public abstract class NiladicFunction<Output> extends MonadicFunction<Object, Output> {

    @Override
    public final Output apply(Object discarded) {
        return apply();
    }

    public abstract Output apply();
}
