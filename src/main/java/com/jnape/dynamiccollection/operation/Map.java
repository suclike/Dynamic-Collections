package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.Predicate;

import java.util.ArrayList;
import java.util.Collection;

public class Map {

    private static final Predicate<Object> TRUE = new Predicate<Object>() {
        @Override
        public Boolean apply(Object anything) {
            return true;
        }
    };

    public static <Input, Output> Collection<Output> map(Collection<Input> collection, Function<? super Input, Output> mapper) {
        return mapWhile(collection, mapper, TRUE);
    }

    public static <Input, Output> Collection<Output> mapWhile(Collection<Input> collection, Function<? super Input, Output> mapper, Predicate<? super Output> predicate) {
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
