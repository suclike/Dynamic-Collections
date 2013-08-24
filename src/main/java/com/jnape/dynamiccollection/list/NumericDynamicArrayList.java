package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.IndexedProcedure;
import com.jnape.dynamiccollection.lambda.MonadicFunction;
import com.jnape.dynamiccollection.lambda.MonadicProcedure;
import com.jnape.dynamiccollection.operation.NumericType;

import java.util.Collection;
import java.util.Iterator;

import static com.jnape.dynamiccollection.lambda.builtin.accumulator.Add.plus;
import static com.jnape.dynamiccollection.lambda.builtin.accumulator.Divide.divide;
import static com.jnape.dynamiccollection.lambda.builtin.accumulator.Multiply.times;
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
    public NumericDynamicArrayList subList(int fromIndex, int toIndex) {
        return new NumericDynamicArrayList(super.subList(fromIndex, toIndex));
    }

    @Override
    public NumericDynamicArrayList concat(Collection<Number> collection) {
        return new NumericDynamicArrayList(super.concat(collection));
    }

    @Override
    public NumericDynamicList concat(Number... numbers) {
        return new NumericDynamicArrayList(super.concat(numbers));
    }

    @Override
    public NumericDynamicList each(IndexedProcedure<? super Number> indexedProcedure) {
        return new NumericDynamicArrayList(super.each(indexedProcedure));
    }

    @Override
    public NumericDynamicList forEach(MonadicProcedure<? super Number> procedure) {
        return new NumericDynamicArrayList(super.forEach(procedure));
    }

    @Override
    public NumericDynamicList filter(MonadicFunction<? super Number, Boolean> filterer) {
        return new NumericDynamicArrayList(super.filter(filterer));
    }

    @Override
    public NumericDynamicList reject(MonadicFunction<? super Number, Boolean> rejector) {
        return new NumericDynamicArrayList(super.reject(rejector));
    }

    @Override
    public NumericDynamicList without(Collection<? super Number> exclusions) {
        return new NumericDynamicArrayList(super.without(exclusions));
    }

    @Override
    public NumericDynamicList unique() {
        return new NumericDynamicArrayList(super.unique());
    }

    @Override
    public <Comparison extends Comparable<Comparison>> NumericDynamicList sort(
            MonadicFunction<? super Number, Comparison> mapper) {
        return new NumericDynamicArrayList(super.sort(mapper));
    }

    @Override
    public NumericDynamicList reverse() {
        return new NumericDynamicArrayList(super.reverse());
    }

    @Override
    public NumericDynamicList scanLeft(Number startingAccumulation, Accumulator<Number, Number> accumulator) {
        return new NumericDynamicArrayList(super.scanLeft(startingAccumulation, accumulator));
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
        return divide(size(), new NumericDynamicArrayList(map(new MonadicFunction<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return divide(1f, number);
            }
        })).sum());
    }

    @Override
    public NumericDynamicList toBytes() {
        return new NumericDynamicArrayList(map(new MonadicFunction<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return number.byteValue();
            }
        }));
    }

    @Override
    public NumericDynamicList toShorts() {
        return new NumericDynamicArrayList(map(new MonadicFunction<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return number.shortValue();
            }
        }));
    }

    @Override
    public NumericDynamicList toIntegers() {
        return new NumericDynamicArrayList(map(new MonadicFunction<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return number.intValue();
            }
        }));
    }

    @Override
    public NumericDynamicList toLongs() {
        return new NumericDynamicArrayList(map(new MonadicFunction<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return number.longValue();
            }
        }));
    }

    @Override
    public NumericDynamicList toFloats() {
        return new NumericDynamicArrayList(map(new MonadicFunction<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return number.floatValue();
            }
        }));
    }

    @Override
    public NumericDynamicList toDoubles() {
        return new NumericDynamicArrayList(map(new MonadicFunction<Number, Number>() {
            @Override
            public Number apply(Number number) {
                return number.doubleValue();
            }
        }));
    }
}
