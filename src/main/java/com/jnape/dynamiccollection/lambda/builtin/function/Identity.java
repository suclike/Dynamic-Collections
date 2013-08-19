package com.jnape.dynamiccollection.lambda.builtin.function;

import com.jnape.dynamiccollection.lambda.Function;

public class Identity<InputOutput> implements Function<InputOutput, InputOutput> {

    public static <InputOutput> Identity<InputOutput> id() {
        return new Identity<InputOutput>();
    }

    @Override
    public InputOutput apply(InputOutput inputOutput) {
        return inputOutput;
    }
}
