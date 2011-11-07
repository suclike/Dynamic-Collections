package org.jnape.dynamiccollection.operator;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class Transformer {

    public <Element, Transformation> DynamicCollection<Transformation> transform(Collection<Element> collection, Function<Element, Transformation> function) {
        DynamicCollection<Transformation> transformation = new DynamicArrayList<Transformation>();

        for (Element element : collection)
            transformation.add(function.apply(element));

        return transformation;
    }
}
