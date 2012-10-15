package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.IndexedProcedure;
import com.jnape.dynamiccollection.lambda.Procedure;
import com.jnape.dynamiccollection.lambda.library.numeric.accumulator.Add;
import com.jnape.dynamiccollection.operation.NumericType;

import java.util.Collection;
import java.util.Iterator;

import static com.jnape.dynamiccollection.lambda.library.numeric.accumulator.Add.plus;
import static com.jnape.dynamiccollection.lambda.library.numeric.accumulator.Divide.divide;
import static com.jnape.dynamiccollection.lambda.library.numeric.accumulator.Multiply.times;
import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;
import static java.lang.Math.pow;

public class NumericDynamicArrayList extends DynamicArrayList<Number> implements NumericDynamicList {

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
    public NumericDynamicList subList(int fromIndex, int toIndex) {
        return numbers(super.subList(fromIndex, toIndex));
    }

    @Override
    public NumericDynamicList concat(Collection<Number> collection) {
        return numbers(super.concat(collection));
    }

    @Override
    public NumericDynamicList concat(Number... numbers) {
        return numbers(super.concat(numbers));
    }

    @Override
    public NumericDynamicList each(IndexedProcedure<? super Number> indexedProcedure) {
        return numbers(super.each(indexedProcedure));
    }

    @Override
    public NumericDynamicList forEach(Procedure<? super Number> procedure) {
        return numbers(super.forEach(procedure));
    }

    @Override
    public NumericDynamicList filter(Function<? super Number, Boolean> filterer) {
        return numbers(super.filter(filterer));
    }

    @Override
    public NumericDynamicList reject(Function<? super Number, Boolean> rejector) {
        return numbers(super.reject(rejector));
    }

    @Override
    public NumericDynamicList without(Collection<? super Number> exclusions) {
        return numbers(super.without(exclusions));
    }

    @Override
    public NumericDynamicList unique() {
        return numbers(super.unique());
    }

    @Override
    public <Comparison extends Comparable<Comparison>> NumericDynamicList sort(Function<? super Number, Comparison> mapper) {
        return numbers(super.sort(mapper));
    }

    @Override
    public NumericDynamicList reverse() {
        return numbers(super.reverse());
    }

    @Override
    public NumericDynamicList map(Function<? super Number, ? extends Number> mapper) {
        return numbers(super.map(mapper));
    }

    @Override
    public NumericDynamicList mapWhile(Function<? super Number, ? extends Number> mapper, Function<? super Number, Boolean> predicate) {
        return numbers(super.mapWhile(mapper, predicate));
    }

    @Override
    public NumericDynamicList scanLeft(Number startingAccumulation, Accumulator<Number, Number> accumulator) {
        return numbers(super.scanLeft(startingAccumulation, accumulator));
    }

    @Override
    public Number sum() {
        return reduce(plus());
    }

    @Override
    public Number product() {
        return reduce(times());
    }

    @Override
    public Number arithmeticMean() {
        return divide(sum(), size());
    }

    @Override
    public Number geometricMean() {
        //TODO: implement NumericType-specific #power method to avoid having to do coercion in List method
        Number product = product();
        NumericType returnType = coercionFor(product);
        return returnType.coerce(pow(product.doubleValue(), 1d / size()));
    }

    @Override
    public Number harmonicMean() {
        return divide(size(), map(new Function<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return divide(1f, number);
            }
        }).sum());
    }

    @Override
    public NumericDynamicList toBytes() {
        return map(new Function<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return number.byteValue();
            }
        });
    }

    @Override
    public NumericDynamicList toShorts() {
        return map(new Function<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return number.shortValue();
            }
        });
    }

    @Override
    public NumericDynamicList toIntegers() {
        return map(new Function<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return number.intValue();
            }
        });
    }

    @Override
    public NumericDynamicList toLongs() {
        return map(new Function<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return number.longValue();
            }
        });
    }

    @Override
    public NumericDynamicList toFloats() {
        return map(new Function<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return number.floatValue();
            }
        });
    }

    @Override
    public NumericDynamicList toDoubles() {
        return map(new Function<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return number.doubleValue();
            }
        });
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

    public static NumericDynamicList fromTo(Number from, Number to, Number increment) {
        if (increment.doubleValue() <= 0)
            throw new IllegalArgumentException("Increment must be greater than 0");

        NumericDynamicList range = new NumericDynamicArrayList();
        NumericType coercion = coercionFor(from, to);

        Number next = from;
        while (next.doubleValue() <= to.doubleValue()) {
            range.add(coercion.coerce(next));
            next = Add.add(next, increment);
        }

        return range;
    }

    public static NumericDynamicList fromTo(Number from, Number to) {
        return fromTo(from, to, 1);
    }
}
