package org.jnape.dynamiccollection;

import org.jnape.dynamiccollection.lambda.Function;

import java.util.List;

public interface DynamicList<Element> extends List<Element>, DynamicCollection<Element> {

    @Override
    <Transformation> DynamicList<Transformation> transform(Function<Element, Transformation> transformer);

    @Override
    DynamicList<Element> collect(Function<Element, Boolean> collector);
}
