package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.MonadicFunction;

import java.util.Collection;

public class All {

    public static <Element> boolean all(Collection<Element> elements,
                                        MonadicFunction<? super Element, Boolean> matcher) {
        for (Element element : elements)
            if (!matcher.apply(element))
                return false;

        return true;
    }
}
