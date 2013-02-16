package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.Predicate;

import java.util.Collection;

import static com.jnape.dynamiccollection.operation.Any.any;

public class None {

    public static <Element> boolean none(Collection<Element> elements, Predicate<? super Element> matcher) {
        return !any(elements, matcher);
    }
}
