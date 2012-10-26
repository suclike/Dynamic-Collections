package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;

import java.util.Collection;

import static com.jnape.dynamiccollection.operation.Any.any;

public class None {

    public static <Element> boolean none(Collection<Element> elements, Function<? super Element, Boolean> matcher) {
        return !any(elements, matcher);
    }
}
