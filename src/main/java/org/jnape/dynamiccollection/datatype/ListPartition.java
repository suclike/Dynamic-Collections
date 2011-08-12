package org.jnape.dynamiccollection.datatype;

import org.jnape.dynamiccollection.list.DynamicArrayList;
import org.jnape.dynamiccollection.list.DynamicList;

import java.util.Collection;

public class ListPartition<Element> implements Partition<Element> {

    private final DynamicList<Element> trues;
    private final DynamicList<Element> falses;

    public ListPartition(Collection<Element> trues, Collection<Element> falses) {
        this.trues = new DynamicArrayList<Element>(trues);
        this.falses = new DynamicArrayList<Element>(falses);
    }

    @Override
    public DynamicList<Element> trues() {
        return trues;
    }

    @Override
    public DynamicList<Element> falses() {
        return falses;
    }
}
