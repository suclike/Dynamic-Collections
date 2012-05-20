package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.operation.Bound;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class ComparableDynamicArrayList<Element extends Comparable<Element>> extends DynamicArrayList<Element> implements ComparableDynamicList<Element> {

    public ComparableDynamicArrayList() {
        super();
    }

    public ComparableDynamicArrayList(Collection<Element> elements) {
        super(elements);
    }

    public ComparableDynamicArrayList(Element... elements) {
        super(elements);
    }

    public ComparableDynamicArrayList(Iterator<Element> iterator) {
        super(iterator);
    }

    @Override
    public Element min() {
        return Bound.min(this);
    }

    @Override
    public Element max() {
        return Bound.max(this);
    }

    @Override
    public ComparableDynamicList<Element> sort() {
        ComparableDynamicList<Element> sorted = new ComparableDynamicArrayList<Element>(this);
        Collections.sort(sorted);
        return sorted;
    }
}
