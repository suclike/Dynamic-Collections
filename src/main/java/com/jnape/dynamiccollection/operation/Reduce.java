package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Accumulator;

import java.util.List;

import static com.jnape.dynamiccollection.operation.Fold.foldLeft;

public class Reduce {

    public static <InputOutput> InputOutput reduce(List<InputOutput> list, Accumulator<InputOutput, InputOutput> accumulator) {
        InputOutput head = list.get(0);
        List<InputOutput> tail = list.subList(1, list.size());

        return foldLeft(tail, head, accumulator);
    }
}
