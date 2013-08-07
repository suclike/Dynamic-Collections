package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;

import java.util.ArrayList;
import java.util.Collection;

import static com.jnape.dynamiccollection.lambda.factory.FunctionFactory.always;

public class Map {

    public static <Input, Output> Collection<Output> map(Collection<Input> collection, Function<? super Input, Output> mapper) {
        return mapWhile(collection, mapper, always(true));
    }

    public static <Input, Output> Collection<Output> mapWhile(Collection<Input> collection, Function<? super Input, Output> mapper, Function<? super Output, Boolean> predicate) {
        Collection<Output> output = new ArrayList<Output>();

        for (Input input : collection) {
            Output mapped = mapper.apply(input);
            if (!predicate.apply(mapped))
                break;
            output.add(mapped);
        }

        return output;
    }
}
