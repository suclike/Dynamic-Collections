package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

public final class Identity<InputOutput> extends MonadicFunction<InputOutput, InputOutput> {

    public static <InputOutput> Identity<InputOutput> id() {
        return new Identity<InputOutput>();
    }

    @Override
    public InputOutput apply(InputOutput inputOutput) {
        return inputOutput;
    }
}
