package org.jnape.dynamiccollection;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;

public class DynamicArrayList<Element> extends ArrayList<Element> implements DynamicList<Element> {

    public DynamicArrayList() {
        super();
    }

    public DynamicArrayList(Collection<Element> elements) {
        super(elements);
    }

    public DynamicArrayList(Element[] elements) {
        super(asList(elements));
    }
}
