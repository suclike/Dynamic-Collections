package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;

import java.util.Collection;

public class Any {

    public static <Element> boolean any(Collection<Element> elements, Function<Element, Boolean> matcher) {
        for (Element element : elements)
            if (matcher.apply(element))
                return true;

        return false;
    }
}
