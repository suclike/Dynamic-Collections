package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;

public class Without {

    @SuppressWarnings("unchecked")
    public static <Element> Collection<Element> without(Collection<Element> collection, Element... exclusions) {
        List<Element> excludedElements = (exclusions != null)
                ? asList(exclusions)
                : asList((Element[]) new Object[]{null});

        Collection<Element> afterExclusion = new ArrayList<Element>(collection);
        afterExclusion.removeAll(excludedElements);
        return afterExclusion;
    }
}
