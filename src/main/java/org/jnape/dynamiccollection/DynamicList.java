package org.jnape.dynamiccollection;

import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.lambda.Procedure;

import java.util.List;

public interface DynamicList<Element> extends List<Element>, DynamicCollection<Element> {

    @Override
    DynamicList<Element> each(Procedure<Element> procedure);

    @Override
    DynamicList<Element> collect(Function<Element, Boolean> collector);

    @Override
    <Transformation> DynamicList<Transformation> transform(Function<Element, Transformation> transformer);

    @Override
    DynamicList<Element> without(Element... subtractions);

    @Override
    DynamicList<Element> unique();
}
