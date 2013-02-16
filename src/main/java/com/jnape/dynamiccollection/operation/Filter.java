package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Predicate;

import java.util.ArrayList;
import java.util.Collection;

public class Filter {

    public static <Element> Collection<Element> filter(Collection<Element> collection, Predicate<? super Element> filterer) {
        Collection<Element> filtered = new ArrayList<Element>();

        for (Element element : collection)
            if (filterer.apply(element))
                filtered.add(element);

        return filtered;
    }
}
