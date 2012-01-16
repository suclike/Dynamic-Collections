package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Reducer {

    public <Element> DynamicCollection<Element> reduce(Collection<Element> collection, Function<Element, Boolean> reductionFunction) {
        DynamicCollection<Element> reduced = new DynamicArrayList<Element>();

        for (Element element : collection)
            if (!reductionFunction.apply(element))
                reduced.add(element);

        return reduced;
    }
}
