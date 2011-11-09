package org.jnape.dynamiccollection.operator;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.list.DynamicArrayList;

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
