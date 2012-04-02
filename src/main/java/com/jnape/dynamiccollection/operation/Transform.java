package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Transform {

    public static <Element, Transformation> DynamicCollection<Transformation> transform(Collection<Element> collection, Function<Element, Transformation> transformer) {
        DynamicCollection<Transformation> transformation = new DynamicArrayList<Transformation>();

        for (Element element : collection)
            transformation.add(transformer.apply(element));

        return transformation;
    }
}
