package com.jnape.dynamiccollection.list.factory;

import com.jnape.dynamiccollection.datatype.tuple.Tuple2;
import com.jnape.dynamiccollection.lambda.NiladicFunction;
import com.jnape.dynamiccollection.lambda.builtin.accumulator.Add;
import com.jnape.dynamiccollection.list.*;
import com.jnape.dynamiccollection.operation.NumericType;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static com.jnape.dynamiccollection.datatype.option.OptionFactory.option;
import static com.jnape.dynamiccollection.datatype.tuple.TupleFactory.tuple;
import static com.jnape.dynamiccollection.operation.NumericType.coercionFor;

public class DynamicListFactory {

    public static <Element> DynamicList<Element> list() {
        return new DynamicArrayList<Element>();
    }

    public static DynamicList<Byte> list(byte[] bytes) {
        DynamicArrayList<Byte> list = new DynamicArrayList<Byte>();
        for (byte b : bytes)
            list.add(b);
        return list;
    }

    public static DynamicList<Short> list(short[] shorts) {
        DynamicArrayList<Short> list = new DynamicArrayList<Short>();
        for (short s : shorts)
            list.add(s);
        return list;
    }

    public static DynamicList<Integer> list(int[] ints) {
        DynamicArrayList<Integer> list = new DynamicArrayList<Integer>();
        for (int i : ints)
            list.add(i);
        return list;
    }

    public static DynamicList<Long> list(long[] longs) {
        DynamicArrayList<Long> list = new DynamicArrayList<Long>();
        for (long l : longs)
            list.add(l);
        return list;
    }

    public static DynamicList<Float> list(float[] floats) {
        DynamicArrayList<Float> list = new DynamicArrayList<Float>();
        for (float f : floats)
            list.add(f);
        return list;
    }

    public static DynamicList<Double> list(double[] doubles) {
        DynamicArrayList<Double> list = new DynamicArrayList<Double>();
        for (double d : doubles)
            list.add(d);
        return list;
    }

    public static DynamicList<Boolean> list(boolean[] booleans) {
        DynamicArrayList<Boolean> list = new DynamicArrayList<Boolean>();
        for (boolean b : booleans)
            list.add(b);
        return list;
    }

    public static DynamicList<Character> list(char[] chars) {
        DynamicArrayList<Character> list = new DynamicArrayList<Character>();
        for (char c : chars)
            list.add(c);
        return list;
    }

    public static <Element> DynamicList<Element> list(Element[] elements) {
        return new DynamicArrayList<Element>(elements);
    }

    public static <Element> DynamicList<Element> list(Element element, Element... elements) {
        return new DynamicArrayList<Element>(element, elements);
    }

    public static <Element> DynamicList<Element> list(Collection<Element> collection) {
        return new DynamicArrayList<Element>(collection);
    }

    public static <Element> DynamicList<Element> list(Iterator<Element> iterator) {
        return new DynamicArrayList<Element>(iterator);
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

    public static <_1, _2> DynamicList<Tuple2<_1, _2>> tuples(Collection<Map.Entry<_1, _2>> entries) {
        DynamicList<Tuple2<_1, _2>> tuples = new DynamicArrayList<Tuple2<_1, _2>>();
        for (Map.Entry<_1, _2> entry : entries)
            tuples.add(tuple(entry.getKey(), entry.getValue()));
        return tuples;
    }

    public static <_1, _2> DynamicList<Tuple2<_1, _2>> tuples(Map<_1, _2> map) {
        return tuples(map.entrySet());
    }

    public static <Output> DynamicList<Output> doTimes(int iterations, NiladicFunction<Output> fn) {
        DynamicList<Output> outputs = new DynamicArrayList<Output>();

        for (int i = 0; i < iterations; i++)
            outputs.add(fn.apply());

        return outputs;
    }

    public static <Output> OptionalDynamicList<Output> options(Output... outputs) {
        OptionalDynamicList<Output> options = new OptionalDynamicArrayList<Output>();

        for (Output output : outputs)
            options.add(option(output));

        return options;
    }
}
