package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

import static java.util.Arrays.asList;

public class Without {

    public static <Element> DynamicCollection<Element> without(Collection<Element> collection, Element... exclusions) {
        DynamicCollection<Element> afterExclusion = new DynamicArrayList<Element>(collection);
        afterExclusion.removeAll(asList(exclusions));
        return afterExclusion;
    }
}
