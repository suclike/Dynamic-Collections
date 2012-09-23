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
    DynamicList<Element> concat(Element... elements);

    @Override
    DynamicList<Element> forEach(Procedure<? super Element> procedure);

    @Override
    DynamicList<Element> filter(Function<? super Element, Boolean> filterer);

    @Override
    DynamicList<Element> reject(Function<? super Element, Boolean> rejector);

    @Override
    <Output> DynamicList<Output> map(Function<? super Element, Output> mapper);

    @Override
    <Output> DynamicList<Output> mapWhile(Function<? super Element, Output> mapper, Function<? super Output, Boolean> predicate);

    @Override
    DynamicList<Element> without(Collection<? super Element> exclusions);

    @Override
    DynamicList<Element> unique();

    @Override
    DynamicCollection<Element> unique(Function<? super Element, ?> mapper);

    DynamicList<DynamicList<Element>> inGroupsOf(int elementsPerGroup);

    DynamicList<DynamicList<Element>> zip(List<? extends Element>... lists);

    DynamicList<DynamicList<Element>> cartesianProduct(List<? extends Element> collection);

    DynamicList<DynamicList<Element>> cartesianProduct();

    <Accumulation> Accumulation foldLeft(Accumulation startingAccumulation, Accumulator<Accumulation, ? super Element> accumulator);

    <Accumulation> Accumulation foldRight(Accumulation startingAccumulation, Accumulator<Accumulation, ? super Element> accumulator);

    Element reduce(Accumulator<Element, ? super Element> accumulator) throws ListWasEmptyException;

    <Accumulation> DynamicList<Accumulation> scanLeft(Accumulation startingAccumulation, Accumulator<Accumulation, ? super Element> accumulator);

    <Comparison extends Comparable<Comparison>> DynamicList<Element> sort(Function<? super Element, Comparison> comparator);

    DynamicList<Element> reverse();

    String join(String combiner);

    Element first() throws ListWasEmptyException;

    Element last() throws ListWasEmptyException;

    <Comparison extends Comparable<Comparison>> Element min(Function<? super Element, Comparison> mapper);

    <Comparison extends Comparable<Comparison>> Element max(Function<? super Element, Comparison> mapper);
}
