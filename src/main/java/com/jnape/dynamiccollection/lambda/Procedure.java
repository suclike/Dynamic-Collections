package com.jnape.dynamiccollection.lambda;

public interface Procedure<Input> {

    void execute(Input input);
}
