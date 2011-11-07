package org.jnape.dynamiccollection.list.operator;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Concatenator {

    public <Element> DynamicCollection<Element> concatenate(Collection<Element> collection1, Collection<Element> collection2) {
        DynamicCollection<Element> concatenation = new DynamicArrayList<Element>();

        concatenation.addAll(collection1);
        concatenation.addAll(collection2);

        return concatenation;
    }
}
