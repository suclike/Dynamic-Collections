package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Unique {

    public static <Element> DynamicCollection<Element> unique(Collection<Element> collection) {
        DynamicCollection<Element> unique = new DynamicArrayList<Element>();

        for (Element element : collection)
            if (!unique.contains(element))
                unique.add(element);

        return unique;
    }
}
