package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;

import java.util.ArrayList;
import java.util.Collection;

public class Transform {

    public static <Element, Transformation> Collection<Transformation> transform(Collection<Element> collection, Function<Element, Transformation> transformer) {
        Collection<Transformation> transformation = new ArrayList<Transformation>();

        for (Element element : collection)
            transformation.add(transformer.apply(element));

        return transformation;
    }
}
