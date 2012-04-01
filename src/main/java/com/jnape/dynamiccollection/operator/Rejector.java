package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Rejector {

    public <Element> DynamicCollection<Element> reject(Collection<Element> collection, Function<Element, Boolean> rejectionFunction) {
        DynamicCollection<Element> notRejected = new DynamicArrayList<Element>();

        for (Element element : collection)
            if (!rejectionFunction.apply(element))
                notRejected.add(element);

        return notRejected;
    }
}
