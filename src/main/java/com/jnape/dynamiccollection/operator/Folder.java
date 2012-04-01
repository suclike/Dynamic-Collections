package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.lambda.HigherOrderFunction;

import java.util.List;

public class Folder {

    public <Input, Output> Output foldRight(List<Input> list, Output startingAccumulation, HigherOrderFunction<Input, Output> accumulator) {
        Output accumulation = startingAccumulation;

        for (Input input : list)
            accumulation = accumulator.apply(input, accumulation);

        return accumulation;
    }

    public <Input, Output> Output foldLeft(List<Input> list, Output startingAccumulation, HigherOrderFunction<Input, Output> accumulator) {
        Output accumulation = startingAccumulation;

        for (int i = list.size(); --i >= 0; )
            accumulation = accumulator.apply(list.get(i), accumulation);

        return accumulation;
    }
}
