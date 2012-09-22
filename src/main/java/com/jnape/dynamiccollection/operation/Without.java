package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.Collection;

public class Without {

    @SuppressWarnings("unchecked")
    public static <Element> Collection<Element> without(Collection<Element> collection, Collection<? super Element> exclusions) {
        Collection<Element> afterExclusion = new ArrayList<Element>(collection);
        afterExclusion.removeAll(exclusions);
        return afterExclusion;
    }
}
