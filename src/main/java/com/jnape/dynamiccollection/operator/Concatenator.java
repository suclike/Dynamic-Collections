package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Concatenator {

    public static <Element> DynamicCollection<Element> concatenate(Collection<Element> collection1, Collection<Element> collection2) {
        DynamicCollection<Element> concatenation = new DynamicArrayList<Element>();

        concatenation.addAll(collection1);
        concatenation.addAll(collection2);

        return concatenation;
    }
}
