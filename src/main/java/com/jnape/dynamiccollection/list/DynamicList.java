package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.Procedure;
import com.jnape.dynamiccollection.list.exception.ListWasEmptyException;

import java.util.Collection;
import java.util.List;

public interface DynamicList<Element> extends DynamicCollection<Element>, List<Element> {

    @Override
    DynamicList<Element> subList(int fromIndex, int toIndex);

    @Override
    DynamicList<Element> concat(Collection<Element> collection);

    @Override
    DynamicList<Element> each(Procedure<Element> procedure);

    @Override
    DynamicList<Element> collect(Function<Element, Boolean> collector);

    @Override
    DynamicList<Element> reject(Function<Element, Boolean> rejector);

    @Override
    <Output> DynamicList<Output> map(Function<Element, Output> mapper);

    @Override
    DynamicList<Element> without(Element... exclusions);

    @Override
    DynamicList<Element> unique();

    DynamicList<DynamicList<Element>> inGroupsOf(int elementsPerGroup);

    DynamicList<DynamicList<Element>> cartesianProduct(List<Element> collection);

    <Accumulation> Accumulation foldLeft(Accumulation startingAccumulation, Accumulator<Accumulation, Element> accumulator);

    <Accumulation> Accumulation foldRight(Accumulation startingAccumulation, Accumulator<Accumulation, Element> accumulator);

    Element reduce(Accumulator<Element, Element> accumulator) throws ListWasEmptyException;

    <Comparison extends Comparable<Comparison>> DynamicList<Element> sort(Function<Element, Comparison> comparator);

    DynamicList<Element> reverse();

    String join(String combiner);

    Element first() throws ListWasEmptyException;

    Element last() throws ListWasEmptyException;

    Element min(Function<Element, Integer> calculator);

    Element max(Function<Element, Integer> calculator);
}
