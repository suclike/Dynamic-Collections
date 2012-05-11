package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;

import java.util.ArrayList;
import java.util.Collection;

public class Map {

    public static <Input, Output> Collection<Output> map(Collection<Input> collection, Function<Input, Output> mapper) {
        Collection<Output> output = new ArrayList<Output>();

        for (Input input : collection)
            output.add(mapper.apply(input));

        return output;
    }
}
