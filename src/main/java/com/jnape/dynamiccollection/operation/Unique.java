package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;

import java.util.ArrayList;
import java.util.Collection;

public class Unique {

    public static <Element> Collection<Element> unique(Collection<Element> collection, final Function<? super Element, ?> mapper) {
        Collection<Element> unique = new ArrayList<Element>();

        //TODO: too dense
        for (final Element element : collection)
            if (!Any.any(unique, new Function<Element, Boolean>() {
                @Override
                public Boolean apply(Element uniqueElement) {
                    return mapper.apply(element).equals(mapper.apply(uniqueElement));
                }
            }))
                unique.add(element);

        return unique;
    }

    public static <Element> Collection<Element> unique(Collection<Element> collection) {
        Collection<Element> unique = new ArrayList<Element>();

        for (Element element : collection)
            if (!unique.contains(element))
                unique.add(element);

        return unique;
    }
}
