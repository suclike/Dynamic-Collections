package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Transform {

    public static <Element, Transformation> DynamicCollection<Transformation> transform(Collection<Element> collection, Function<Element, Transformation> transformationFunction) {
        DynamicCollection<Transformation> transformation = new DynamicArrayList<Transformation>();

        for (Element element : collection)
            transformation.add(transformationFunction.apply(element));

        return transformation;
    }
}
