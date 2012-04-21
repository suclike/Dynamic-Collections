package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;
import java.util.List;

public class Without {

    @SuppressWarnings("unchecked")
    public static <Element> DynamicCollection<Element> without(Collection<Element> collection, Element... exclusions) {
        List<Element> excludedElements = (exclusions != null)
                ? new DynamicArrayList<Element>(exclusions)
                : new DynamicArrayList<Element>((Element[]) new Object[]{null});

        DynamicCollection<Element> afterExclusion = new DynamicArrayList<Element>(collection);
        afterExclusion.removeAll(excludedElements);
        return afterExclusion;
    }
}
