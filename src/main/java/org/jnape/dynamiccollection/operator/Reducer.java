package org.jnape.dynamiccollection.operator;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Reducer {

    public <Element> DynamicCollection<Element> reduce(Collection<Element> collection, Function<Element, Boolean> function) {
        DynamicCollection<Element> reduced = new DynamicArrayList<Element>();

        for (Element element : collection)
            if (!function.apply(element))
                reduced.add(element);

        return reduced;
    }
}
