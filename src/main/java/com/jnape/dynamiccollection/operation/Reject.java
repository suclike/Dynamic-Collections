package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;

import java.util.ArrayList;
import java.util.Collection;

public class Reject {

    public static <Element> Collection<Element> reject(Collection<Element> collection, Function<Element, Boolean> rejector) {
        Collection<Element> notRejected = new ArrayList<Element>();

        for (Element element : collection)
            if (!rejector.apply(element))
                notRejected.add(element);

        return notRejected;
    }
}
