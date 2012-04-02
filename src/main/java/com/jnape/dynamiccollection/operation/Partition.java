package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Partition {

    public static <Element> com.jnape.dynamiccollection.datatype.Partition<Element> partition(Collection<Element> collection, Function<Element, Boolean> partitioner) {
        DynamicCollection<Element> trues = new DynamicArrayList<Element>();
        DynamicCollection<Element> falses = new DynamicArrayList<Element>();

        for (Element element : collection)
            (partitioner.apply(element) ? trues : falses).add(element);

        return new com.jnape.dynamiccollection.datatype.Partition<Element>(trues, falses);
    }
}
