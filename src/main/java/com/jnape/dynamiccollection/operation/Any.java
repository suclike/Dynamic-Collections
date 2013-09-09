package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import java.util.Collection;

import static com.jnape.dynamiccollection.lambda.niladic.builtin.Always.always;

public class Any {

    public static <Element> boolean any(Collection<Element> elements,
                                        MonadicFunction<? super Element, Boolean> matcher) {
        return anyWhile(elements, matcher, always(true));
    }

    public static <Element> boolean anyWhile(Collection<Element> elements,
                                             MonadicFunction<? super Element, Boolean> matcher,
                                             MonadicFunction<? super Element, Boolean> predicate) {
        for (Element element : elements) {
            if (!predicate.apply(element))
                return false;

            if (matcher.apply(element))
                return true;
        }

        return false;
    }
}
