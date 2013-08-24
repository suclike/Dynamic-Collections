package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.MonadicFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {

    public static <Element, Comparison extends Comparable<Comparison>> List<Element> sort(List<Element> elements,
                                                                                          final MonadicFunction<? super Element, Comparison> mapper) {
        Comparator<Element> internalComparator = new Comparator<Element>() {
            @Override
            public int compare(Element a, Element b) {
                Comparison comparableA = mapper.apply(a);
                Comparison comparableB = mapper.apply(b);

                return comparableA.compareTo(comparableB);
            }
        };

        List<Element> sorted = new ArrayList<Element>(elements);
        Collections.sort(sorted, internalComparator);
        return sorted;
    }
}
