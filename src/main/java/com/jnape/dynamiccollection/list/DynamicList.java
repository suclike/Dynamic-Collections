package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.Procedure;
import com.jnape.dynamiccollection.list.exception.ListNotSortableWithoutCustomComparatorException;
import com.jnape.dynamiccollection.list.exception.ListWasEmptyException;

import java.util.Collection;
import java.util.List;

public interface DynamicList<Element> extends List<Element>, DynamicCollection<Element> {

    @Override
    DynamicList<Element> subList(int fromIndex, int toIndex);

    @Override
    DynamicList<Element> concat(Collection<Element> collection);

    @Override
    DynamicList<Element> each(Procedure<Element> iterativeProcedure);

    @Override
    DynamicList<Element> collect(Function<Element, Boolean> collectionFunction);

    @Override
    DynamicList<Element> reduce(Function<Element, Boolean> reductionFunction);

    @Override
    <Transformation> DynamicList<Transformation> transform(Function<Element, Transformation> transformationFunction);

    @Override
    DynamicList<Element> without(Element... exclusions);

    @Override
    Partition<Element> partition(Function<Element, Boolean> sieve);

    @Override
    DynamicList<Element> unique();

    DynamicList<DynamicCollection<Element>> cartesianProduct(Collection<Element> collection);

    DynamicList<Element> reverse();

    DynamicList<Element> sort() throws ListNotSortableWithoutCustomComparatorException;

    <Comparison extends Comparable<Comparison>> DynamicList<Element> sort(Function<Element, Comparison> comparator);

    Element first() throws ListWasEmptyException;

    Element last() throws ListWasEmptyException;

    Element max(Function<Element, Integer> calculator);

    Element min(Function<Element, Integer> calculator);
    
    String join(String combiner);
}
