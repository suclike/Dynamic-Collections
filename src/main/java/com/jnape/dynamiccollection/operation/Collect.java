package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;

import java.util.ArrayList;
import java.util.Collection;

public class Collect {

    public static <Element> Collection<Element> collect(Collection<Element> collection, Function<? super Element, Boolean> collector) {
        Collection<Element> collected = new ArrayList<Element>();

        for (Element element : collection)
            if (collector.apply(element))
                collected.add(element);

        return collected;
    }
}
