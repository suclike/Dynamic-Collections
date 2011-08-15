package org.jnape.dynamiccollection;

import org.jnape.dynamiccollection.list.DynamicArrayList;
import org.jnape.dynamiccollection.list.DynamicList;
import org.jnape.dynamiccollection.set.DynamicHashSet;

import java.util.Collection;
import java.util.Set;

public class DynamicCollectionFactory {

    private DynamicCollectionFactory() {
    }

    public static <Element> DynamicCollection<Element> with(Collection<Element> collection) {
        if (collection instanceof Set)
            return new DynamicHashSet<Element>(collection);

        return new DynamicArrayList<Element>(collection);
    }

    public static <Element> DynamicList<Element> list() {
        return new DynamicArrayList<Element>();
    }

    public static <Element> DynamicList<Element> list(Collection<Element> collection) {
        return new DynamicArrayList<Element>(collection);
    }

    public static <Element> DynamicList<Element> list(Element... elements) {
        return new DynamicArrayList<Element>(elements);
    }
}
