package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.dyadic.DyadicFunction;
import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import org.apache.commons.lang.mutable.MutableInt;

import java.util.ArrayList;
import java.util.Collection;

import static com.jnape.dynamiccollection.lambda.niladic.builtin.Always.always;

public class Map {

    public static <Input, Output> Collection<Output> map(Collection<Input> collection,
                                                         final DyadicFunction<Number, ? super Input, Output> mapper) {
        final MutableInt index = new MutableInt(-1);
        return map(collection, new MonadicFunction<Input, Output>() {
            @Override
            public Output apply(Input input) {
                index.add(1);
                return mapper.apply(index.intValue(), input);
            }
        });
    }

    public static <Input, Output> Collection<Output> map(Collection<Input> collection,
                                                         final MonadicFunction<? super Input, Output> mapper) {
        return mapWhile(collection, mapper, always(true));
    }

    public static <Input, Output> Collection<Output> mapWhile(Collection<Input> collection,
                                                              MonadicFunction<? super Input, Output> mapper,
                                                              MonadicFunction<? super Output, Boolean> predicate) {
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
