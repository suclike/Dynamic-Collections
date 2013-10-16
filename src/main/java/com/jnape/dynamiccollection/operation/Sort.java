package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {

    @SuppressWarnings("unchecked")
    public static <Element> List<Element> sort(List<Element> elements,
                                               final MonadicFunction<? super Element, ? extends Comparable>... mappers) {
        List<Element> sorted = new ArrayList<Element>(elements);

        Comparator<Element> comparator = new Comparator<Element>() {
            @Override
            public int compare(Element a, Element b) {
                int comparison = 0;

                int i = -1;
                while (++i < mappers.length && comparison == 0)
                    comparison = mappers[i].apply(a).compareTo(mappers[i].apply(b));

                return comparison;
            }
        };

        Collections.sort(sorted, comparator);
        return sorted;
    }
}
