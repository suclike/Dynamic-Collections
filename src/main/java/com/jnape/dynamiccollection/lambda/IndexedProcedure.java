package com.jnape.dynamiccollection.lambda;

public interface IndexedProcedure<Input> {

    void execute(Input input, Integer index);
}
