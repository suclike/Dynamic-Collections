package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.MonadicFunction;

import java.util.Collection;

import static com.jnape.dynamiccollection.operation.Any.any;

public class None {

    public static <Element> boolean none(Collection<Element> elements,
                                         MonadicFunction<? super Element, Boolean> matcher) {
        return !any(elements, matcher);
    }
}
