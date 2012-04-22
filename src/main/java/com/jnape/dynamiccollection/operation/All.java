package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;

import java.util.Collection;

public class All {

    public static <Element> boolean all(Collection<Element> elements, final Function<Element, Boolean> matcher) {
        for (Element element : elements)
            if (!matcher.apply(element))
                return false;

        return true;
    }
}
