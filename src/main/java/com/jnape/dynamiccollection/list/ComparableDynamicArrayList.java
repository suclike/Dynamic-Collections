package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.operation.Bound;
import com.jnape.dynamiccollection.operation.Sort;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

//TODO: Not very useful. Revisit this whole thing
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
        List<Element> sorted = Sort.sort(this);
        return new ComparableDynamicArrayList<Element>(sorted);
    }
}
