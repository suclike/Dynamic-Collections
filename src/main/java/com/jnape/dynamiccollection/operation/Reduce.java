package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;
import com.jnape.dynamiccollection.list.exception.ListWasEmptyException;

import java.util.List;

import static com.jnape.dynamiccollection.operation.Fold.foldLeft;

public class Reduce {

    public static <InputOutput> InputOutput reduce(List<InputOutput> list,
                                                   Accumulator<InputOutput, ? super InputOutput> accumulator) {
        if (list.isEmpty())
            throw new ListWasEmptyException();

        InputOutput head = list.get(0);
        List<InputOutput> tail = list.subList(1, list.size());

        return foldLeft(tail, head, accumulator);
    }
}
