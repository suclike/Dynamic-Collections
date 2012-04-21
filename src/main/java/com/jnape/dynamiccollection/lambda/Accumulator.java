package com.jnape.dynamiccollection.lambda;

public interface Accumulator<Output, Input> {

    Output apply(Output accumulation, Input input);
}
