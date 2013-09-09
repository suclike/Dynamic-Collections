package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.*;
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
    DynamicList<Element> each(IndexedProcedure<? super Element> indexedProcedure);

    @Override
    DynamicList<Element> forEach(MonadicProcedure<? super Element> procedure);

    @Override
    DynamicList<Element> filter(MonadicFunction<? super Element, Boolean> filterer);

    @Override
    DynamicList<Element> reject(MonadicFunction<? super Element, Boolean> rejector);

    @Override
    <Output> DynamicList<Output> map(MonadicFunction<? super Element, Output> mapper);

    @Override
    <Output> DynamicList<Output> map(DyadicFunction<Number, ? super Element, Output> indexedMapper);

    @Override
    <Output> DynamicList<Output> mapWhile(MonadicFunction<? super Element, Output> mapper,
                                          MonadicFunction<? super Output, Boolean> predicate);

    @Override
    DynamicList<Element> without(Collection<? super Element> exclusions);

    @Override
    DynamicList<Element> unique();

    @Override
    DynamicList<Element> unique(MonadicFunction<? super Element, ?> mapper);

    @Override
    DynamicList<Element> duplicates();

    @Override
    DynamicList<DynamicList<Element>> group();

    @Override
    DynamicList<DynamicList<Element>> group(MonadicFunction<? super Element, ?> mapper);

    DynamicList<DynamicList<Element>> inGroupsOf(int elementsPerGroup);

    DynamicList<DynamicList<Element>> allSequencesOf(int elementsPerSequences);

    DynamicList<DynamicList<Element>> zip(List<? extends Element>... lists);

    DynamicList<DynamicList<Element>> cartesianProduct(List<? extends Element> collection);

    DynamicList<DynamicList<Element>> cartesianProduct();

    <Accumulation> Accumulation foldLeft(Accumulation startingAccumulation,
                                         Accumulator<Accumulation, ? super Element> accumulator);

    <Accumulation> Accumulation foldRight(Accumulation startingAccumulation,
                                          Accumulator<Accumulation, ? super Element> accumulator);

    Element reduce(Accumulator<Element, ? super Element> accumulator) throws ListWasEmptyException;

    <Accumulation> DynamicList<Accumulation> scanLeft(Accumulation startingAccumulation,
                                                      Accumulator<Accumulation, ? super Element> accumulator);

    DynamicList<Element> scanLeft(Accumulator<Element, ? super Element> accumulator);

    <Comparison extends Comparable<Comparison>> DynamicList<Element> sort(
            MonadicFunction<? super Element, Comparison> mapper);

    DynamicList<Element> reverse();

    String join(String combiner);

    Element first() throws ListWasEmptyException;

    Element last() throws ListWasEmptyException;

    <Comparison extends Comparable<Comparison>> Element min(MonadicFunction<? super Element, Comparison> mapper);

    <Comparison extends Comparable<Comparison>> Element max(MonadicFunction<? super Element, Comparison> mapper);
}
