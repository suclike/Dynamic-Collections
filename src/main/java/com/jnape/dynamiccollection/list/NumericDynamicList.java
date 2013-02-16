package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.lambda.*;

import java.util.Collection;

public interface NumericDynamicList extends DynamicList<Number> {

    @Override
    NumericDynamicList subList(int fromIndex, int toIndex);

    @Override
    NumericDynamicList concat(Collection<Number> collection);

    @Override
    NumericDynamicList concat(Number... numbers);

    @Override
    NumericDynamicList each(IndexedProcedure<? super Number> indexedProcedure);

    @Override
    NumericDynamicList forEach(Procedure<? super Number> procedure);

    @Override
    NumericDynamicList filter(Predicate<? super Number> filterer);

    @Override
    NumericDynamicList reject(Predicate<? super Number> rejector);

    @Override
    NumericDynamicList without(Collection<? super Number> exclusions);

    @Override
    NumericDynamicList unique();

    @Override
    <Comparison extends Comparable<Comparison>> NumericDynamicList sort(Function<? super Number, Comparison> comparator);

    @Override
    NumericDynamicList reverse();

    NumericDynamicList map(Function<? super Number, ? extends Number> mapper);

    NumericDynamicList mapWhile(Function<? super Number, ? extends Number> mapper, Predicate<? super Number> predicate);

    NumericDynamicList scanLeft(Number startingAccumulation, Accumulator<Number, Number> accumulator);

    Number sum();

    Number product();

    Number arithmeticMean();

    Number geometricMean();

    Number harmonicMean();

    NumericDynamicList toBytes();

    NumericDynamicList toShorts();

    NumericDynamicList toIntegers();

    NumericDynamicList toLongs();

    NumericDynamicList toFloats();

    NumericDynamicList toDoubles();
}
