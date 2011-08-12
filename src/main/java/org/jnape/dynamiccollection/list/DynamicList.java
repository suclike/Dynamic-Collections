package org.jnape.dynamiccollection.list;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.datatype.ListPartition;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.lambda.Procedure;

import java.util.Collection;
import java.util.List;

public interface DynamicList<Element> extends List<Element>, DynamicCollection<Element> {

    @Override
    DynamicList<Element> subList(int fromIndex, int toIndex);

    @Override
    DynamicList<Element> concat(Collection<Element> collection);

    @Override
    DynamicList<DynamicList<Element>> cartesianProduct(Collection<Element> collection);

    @Override
    DynamicList<Element> each(Procedure<Element> procedure);

    @Override
    DynamicList<Element> collect(Function<Element, Boolean> collector);

    @Override
    <Transformation> DynamicList<Transformation> transform(Function<Element, Transformation> transformer);

    @Override
    DynamicList<Element> without(Element... subtractions);

    @Override
    ListPartition<Element> partition(Function<Element, Boolean> sieve);

    @Override
    DynamicList<Element> unique();
}
