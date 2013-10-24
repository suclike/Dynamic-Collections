package com.jnape.dynamiccollection.lambda.niladic;

import com.jnape.dynamiccollection.lambda.monadic.MonadicProcedure;

public abstract class NiladicProcedure extends MonadicProcedure<Object> {

    @Override
    public final void execute(Object ignored) {
        execute();
    }

    public abstract void execute();
}
