package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.IndexedProcedure;
import com.jnape.dynamiccollection.lambda.Procedure;
import com.jnape.dynamiccollection.operation.NumericType;

import java.util.Collection;
import java.util.Iterator;

import static com.jnape.dynamiccollection.lambda.accumulator.Arithmetic.PLUS;

public class NumericDynamicArrayList extends DynamicArrayList<Number> {

    public NumericDynamicArrayList(Number... numbers) {
        super(numbers);
    }

    public NumericDynamicArrayList(Collection<? extends Number> numbers) {
        super(numbers);
    }

    public NumericDynamicArrayList(Iterator<? extends Number> iterator) {
        super(iterator);
    }

    @Override
    public NumericDynamicArrayList subList(int fromIndex, int toIndex) {
        return numbers(super.subList(fromIndex, toIndex));
    }

    @Override
    public NumericDynamicArrayList concat(Collection<Number> collection) {
        return numbers(super.concat(collection));
    }

    @Override
    public NumericDynamicArrayList concat(Number... numbers) {
        return numbers(super.concat(numbers));
    }

    @Override
    public DynamicCollection<Number> each(IndexedProcedure<? super Number> indexedProcedure) {
        return numbers(super.each(indexedProcedure));
    }

    @Override
    public NumericDynamicArrayList forEach(Procedure<? super Number> procedure) {
        return numbers(super.forEach(procedure));
    }

    @Override
    public NumericDynamicArrayList filter(Function<? super Number, Boolean> filterer) {
        return numbers(super.filter(filterer));
    }

    @Override
    public NumericDynamicArrayList reject(Function<? super Number, Boolean> rejector) {
        return numbers(super.reject(rejector));
    }

    public NumericDynamicArrayList map(Function<? super Number, ? extends Number> mapper) {
        return numbers(super.map(mapper));
    }

    public NumericDynamicArrayList mapWhile(Function<? super Number, ? extends Number> mapper, Function<? super Number, Boolean> predicate) {
        return numbers(super.mapWhile(mapper, predicate));
    }

    @Override
    public NumericDynamicArrayList without(Number... exclusions) {
        return numbers(super.without(exclusions));
    }

    @Override
    public NumericDynamicArrayList unique() {
        return numbers(super.unique());
    }

    @Override
    public <Comparison extends Comparable<Comparison>> DynamicList<Number> sort(Function<? super Number, Comparison> comparator) {
        return numbers(super.sort(comparator));
    }

    @Override
    public NumericDynamicArrayList reverse() {
        return numbers(super.reverse());
    }

    public static NumericDynamicArrayList numbers(Number... numbers) {
        return new NumericDynamicArrayList(numbers);
    }

    public static NumericDynamicArrayList numbers(Iterator<? extends Number> numbers) {
        return new NumericDynamicArrayList(numbers);
    }

    public static NumericDynamicArrayList numbers(Collection<? extends Number> numbers) {
        return new NumericDynamicArrayList(numbers);
    }

    public static NumericDynamicArrayList fromTo(Number from, Number to) {
        NumericDynamicArrayList range = new NumericDynamicArrayList();
        NumericType coercion = NumericType.coercionFor(from, to);

        Number next = from;
        while (next.doubleValue() <= to.doubleValue()) {
            range.add(coercion.coerce(next));
            next = PLUS.apply(next, (byte) 1);
        }

        return range;
    }
}
