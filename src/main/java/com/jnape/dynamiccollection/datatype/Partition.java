package com.jnape.dynamiccollection.datatype;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.datatype.tuple.Tuple2;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Partition<Element> extends Tuple2<DynamicCollection<Element>, DynamicCollection<Element>> {

    public Partition(Collection<Element> trues, Collection<Element> falses) {
        super(new DynamicArrayList<Element>(trues), new DynamicArrayList<Element>(falses));
    }

    public DynamicCollection<Element> trues() {
        return _1;
    }

    public DynamicCollection<Element> falses() {
        return _2;
    }
}
