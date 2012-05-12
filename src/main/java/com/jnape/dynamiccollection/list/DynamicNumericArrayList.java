package com.jnape.dynamiccollection.list;

import java.util.Collection;
import java.util.Iterator;

public class DynamicNumericArrayList<Element extends Number> extends DynamicArrayList<Element> {

    public DynamicNumericArrayList() {
        super();
    }

    public DynamicNumericArrayList(Collection<Element> elements) {
        super(elements);
    }

    public DynamicNumericArrayList(Element... elements) {
        super(elements);
    }

    public DynamicNumericArrayList(Iterator<Element> iterator) {
        super(iterator);
    }
}
