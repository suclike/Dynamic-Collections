package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;
import com.jnape.dynamiccollection.lambda.dyadic.IndexedProcedure;
import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import com.jnape.dynamiccollection.lambda.monadic.MonadicProcedure;

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
    NumericDynamicList sort(MonadicFunction<? super Number, ? extends Comparable>... mappers);

    @Override
    NumericDynamicList reverse();

    NumericDynamicList scanLeft(Number startingAccumulation, Accumulator<Number, Number> accumulator);

    Number sum();

    Number product();

    Number arithmeticMean();

    Number geometricMean();

    Number harmonicMean();

    DynamicList<Byte> toBytes();

    DynamicList<Short> toShorts();

    DynamicList<Integer> toIntegers();

    DynamicList<Long> toLongs();

    DynamicList<Float> toFloats();

    DynamicList<Double> toDoubles();
}
