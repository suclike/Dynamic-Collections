package org.jnape.dynamiccollection;

import java.util.Collection;

public class DynamicCollectionFactory {

    private DynamicCollectionFactory() {}

    public static <Element> DynamicCollection<Element> with(Collection<Element> collection) {
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
