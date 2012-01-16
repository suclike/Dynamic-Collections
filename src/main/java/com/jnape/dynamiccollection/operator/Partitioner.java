package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Partitioner {

    public <Element> Partition<Element> partition(Collection<Element> collection, Function<Element, Boolean> partitionFunction) {
        DynamicCollection<Element> trues = new DynamicArrayList<Element>();
        DynamicCollection<Element> falses = new DynamicArrayList<Element>();

        for (Element element : collection)
            (partitionFunction.apply(element) ? trues : falses).add(element);

        return new Partition<Element>(trues, falses);
    }
}
