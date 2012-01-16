package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Collector {

    public <Element> DynamicCollection<Element> collect(Collection<Element> collection, Function<Element, Boolean> collectionFunction) {
        DynamicCollection<Element> collected = new DynamicArrayList<Element>();

        for (Element element : collection)
            if (collectionFunction.apply(element))
                collected.add(element);

        return collected;
    }
}
