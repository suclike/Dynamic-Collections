package org.jnape.dynamiccollection.list.operation;

import org.jnape.dynamiccollection.list.DynamicArrayList;
import org.jnape.dynamiccollection.list.DynamicList;

import java.util.Collection;

public class Concatenation {

    public <Element> DynamicList<Element> execute(Collection<Element> collection1, Collection<Element> collection2) {
        DynamicList<Element> concatenation = new DynamicArrayList<Element>();

        concatenation.addAll(collection1);
        concatenation.addAll(collection2);

        return concatenation;
    }
}
