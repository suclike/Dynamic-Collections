package org.jnape.dynamiccollection;

import org.jnape.dynamiccollection.lambda.Function;

import java.util.Collection;

public interface DynamicCollection<Element> extends Collection<Element> {

    <Transformation> DynamicCollection<Transformation> transform(Function<Element, Transformation> transformer);

    DynamicCollection<Element> collect(Function<Element, Boolean> collector);
}
