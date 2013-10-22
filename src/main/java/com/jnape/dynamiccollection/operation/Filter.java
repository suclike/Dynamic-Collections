package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.dyadic.DyadicFunction;
import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import java.util.ArrayList;
import java.util.Collection;

public class Filter {

    public static <Element> Collection<Element> filter(Collection<Element> collection,
                                                       final MonadicFunction<? super Element, Boolean> filterer) {
        return filter(collection, new DyadicFunction<Integer, Element, Boolean>() {
            @Override
            public Boolean apply(Integer integer, Element element) {
                return filterer.apply(element);
            }
        });
    }

    public static <Element> Collection<Element> filter(Collection<Element> collection,
                                                       DyadicFunction<Integer, ? super Element, Boolean> filterer) {
        Collection<Element> filtered = new ArrayList<Element>();

        int index = 0;
        for (Element element : collection)
            if (filterer.apply(index++, element))
                filtered.add(element);

        return filtered;
    }
}
