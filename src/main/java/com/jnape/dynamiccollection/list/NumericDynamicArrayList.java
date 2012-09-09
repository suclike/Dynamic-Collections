package com.jnape.dynamiccollection.list;

import java.util.Collection;
import java.util.Iterator;

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

    public static NumericDynamicArrayList numbers(Number... numbers) {
        return new NumericDynamicArrayList(numbers);
    }

    public static NumericDynamicArrayList numbers(Iterator<? extends Number> numbers) {
        return new NumericDynamicArrayList(numbers);
    }

    public static NumericDynamicArrayList numbers(Collection<? extends Number> numbers) {
        return new NumericDynamicArrayList(numbers);
    }

}
