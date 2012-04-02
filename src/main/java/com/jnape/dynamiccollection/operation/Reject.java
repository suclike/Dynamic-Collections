package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Reject {

    public static <Element> DynamicCollection<Element> reject(Collection<Element> collection, Function<Element, Boolean> rejector) {
        DynamicCollection<Element> notRejected = new DynamicArrayList<Element>();

        for (Element element : collection)
            if (!rejector.apply(element))
                notRejected.add(element);

        return notRejected;
    }
}
