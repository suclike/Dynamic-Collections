package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Accumulator;

import java.util.List;

public class Fold {

    public static <Input, Output> Output foldLeft(List<Input> list, Output startingAccumulation, Accumulator<Output, ? super Input> accumulator) {
        Output accumulation = startingAccumulation;

        for (Input input : list)
            accumulation = accumulator.apply(accumulation, input);

        return accumulation;
    }

    public static <Input, Output> Output foldRight(List<Input> list, Output startingAccumulation, Accumulator<Output, ? super Input> accumulator) {
        Output accumulation = startingAccumulation;

        for (int i = list.size(); --i >= 0; )
            accumulation = accumulator.apply(accumulation, list.get(i));

        return accumulation;
    }
}
