package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.IndexedProcedure;
import com.jnape.dynamiccollection.lambda.MonadicFunction;
import com.jnape.dynamiccollection.lambda.MonadicProcedure;

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
    NumericDynamicList forEach(MonadicProcedure<? super Number> procedure);

    @Override
    NumericDynamicList filter(MonadicFunction<? super Number, Boolean> filterer);

    @Override
    NumericDynamicList reject(MonadicFunction<? super Number, Boolean> rejector);

    @Override
    NumericDynamicList without(Collection<? super Number> exclusions);

    @Override
    NumericDynamicList unique();

    @Override
    <Comparison extends Comparable<Comparison>> NumericDynamicList sort(
            MonadicFunction<? super Number, Comparison> comparator);

    @Override
    NumericDynamicList reverse();

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
