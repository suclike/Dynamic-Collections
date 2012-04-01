package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.HigherOrderFunction;
import com.jnape.dynamiccollection.lambda.Procedure;
import com.jnape.dynamiccollection.list.exception.ListNotSortableWithoutCustomComparatorException;
import com.jnape.dynamiccollection.list.exception.ListWasEmptyException;

import java.util.Collection;
import java.util.List;

public interface DynamicList<Element> extends DynamicCollection<Element>, List<Element> {

    @Override
    DynamicList<Element> concat(Collection<Element> collection);

    @Override
    DynamicList<Element> each(Procedure<Element> procedure);

    @Override
    DynamicList<Element> collect(Function<Element, Boolean> collector);

    @Override
    DynamicList<Element> reject(Function<Element, Boolean> rejector);

    @Override
    <Transformation> DynamicList<Transformation> transform(Function<Element, Transformation> transformer);

    @Override
    DynamicList<Element> without(Element... exclusions);

    @Override
    Partition<Element> partition(Function<Element, Boolean> partitioner);

    @Override
    DynamicList<Element> unique();

    @Override
    DynamicList<Element> subList(int fromIndex, int toIndex);

    DynamicList<DynamicList<Element>> cartesianProduct(List<Element> collection);

    <Accumulation> Accumulation foldRight(Accumulation startingAccumulation, HigherOrderFunction<Element, Accumulation> accumulator);

    <Accumulation> Accumulation foldLeft(Accumulation startingAccumulation, HigherOrderFunction<Element, Accumulation> accumulator);

    <Comparison extends Comparable<Comparison>> DynamicList<Element> sort(Function<Element, Comparison> comparator);

    DynamicList<Element> sort() throws ListNotSortableWithoutCustomComparatorException;

    DynamicList<Element> reverse();

    String join(String combiner);

    Element first() throws ListWasEmptyException;

    Element last() throws ListWasEmptyException;

    Element min(Function<Element, Integer> calculator);

    Element max(Function<Element, Integer> calculator);
}
