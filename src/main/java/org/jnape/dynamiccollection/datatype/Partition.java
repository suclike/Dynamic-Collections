package org.jnape.dynamiccollection.datatype;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Partition<Element> {

    private final DynamicCollection<Element> trues;
    private final DynamicCollection<Element> falses;

    public Partition(Collection<Element> trues, Collection<Element> falses) {
        this.trues = new DynamicArrayList<Element>(trues);
        this.falses = new DynamicArrayList<Element>(falses);
    }

    public DynamicCollection<Element> trues() {
        return trues;
    }

    public DynamicCollection<Element> falses() {
        return falses;
    }
}
