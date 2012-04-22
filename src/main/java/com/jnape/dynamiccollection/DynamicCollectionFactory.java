package com.jnape.dynamiccollection;

import com.jnape.dynamiccollection.list.DynamicArrayList;
import com.jnape.dynamiccollection.list.DynamicList;

import java.util.Collection;
import java.util.Iterator;

public class DynamicCollectionFactory {

    private DynamicCollectionFactory() {
    }

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

    public static <Element> DynamicList<Element> list(Iterator<Element> iterator) {
        return new DynamicArrayList<Element>(iterator);
    }
}
