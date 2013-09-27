package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.datatype.option.Option;
import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import java.util.Collection;

import static com.jnape.dynamiccollection.datatype.option.None.none;
import static com.jnape.dynamiccollection.datatype.option.Some.some;

public class Find {
    public static <Element> Option<Element> find(MonadicFunction<? super Element, Boolean> predicate,
                                                 Collection<? extends Element> elements) {
        for (Element element : elements)
            if (predicate.apply(element))
                return some(element);

        return none();
    }
}
