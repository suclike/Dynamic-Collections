package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.Collection;

public class Unique {

    public static <Element> Collection<Element> unique(Collection<Element> collection) {
        Collection<Element> unique = new ArrayList<Element>();

        for (Element element : collection)
            if (!unique.contains(element))
                unique.add(element);

        return unique;
    }
}
