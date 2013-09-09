package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;
import com.jnape.dynamiccollection.list.DynamicList;

import java.util.List;

import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.list;
import static com.jnape.dynamiccollection.operation.Fold.foldLeft;

public class Scan {

    public static <Element> List<Element> scanLeft(List<Element> elements,
                                                   Accumulator<Element, ? super Element> scanner) {
        if (elements.size() == 0)
            return list();

        Element head = elements.get(0);
        List<Element> tail = elements.subList(1, elements.size());
        return scanLeft(tail, head, scanner);
    }

    @SuppressWarnings("unchecked")
    public static <Element, Output> List<Output> scanLeft(List<Element> elements, Output startingAccumulation,
                                                          final Accumulator<Output, ? super Element> scanner) {
        return foldLeft(elements, list(startingAccumulation), new Accumulator<DynamicList<Output>, Element>() {
            @Override
            public DynamicList<Output> apply(DynamicList<Output> accumulation, Element element) {
                accumulation.add(scanner.apply(accumulation.last(), element));
                return accumulation;
            }
        });
    }
}
