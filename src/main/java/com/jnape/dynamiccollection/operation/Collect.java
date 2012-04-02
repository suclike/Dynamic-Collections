package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Collect {

    public static <Element> DynamicCollection<Element> collect(Collection<Element> collection, Function<Element, Boolean> collector) {
        DynamicCollection<Element> collected = new DynamicArrayList<Element>();

        for (Element element : collection)
            if (collector.apply(element))
                collected.add(element);

        return collected;
    }
}
