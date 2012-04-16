package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.HigherOrderFunction;
import com.jnape.dynamiccollection.lambda.Procedure;
import com.jnape.dynamiccollection.list.exception.ListNotSortableWithoutCustomComparatorException;
import com.jnape.dynamiccollection.list.exception.ListWasEmptyException;
import com.jnape.dynamiccollection.operation.*;

import java.util.*;

public class DynamicArrayList<Element> extends ArrayList<Element> implements DynamicList<Element> {

    public DynamicArrayList() {
        super();
    }

    public DynamicArrayList(Collection<Element> elements) {
        super(elements);
    }

    public DynamicArrayList(Element... elements) {
        super();
        Collections.addAll(this, elements);
    }

    @Override
    public DynamicList<Element> concat(Collection<Element> collection) {
        return (DynamicList<Element>) Concatenate.concatenate(this, collection);
    }

    @Override
    public DynamicList<Element> each(Procedure<Element> procedure) {
        Each.each(this, procedure);
        return this;
    }

    @Override
    public DynamicList<Element> collect(Function<Element, Boolean> collector) {
        return (DynamicList<Element>) Collect.collect(this, collector);
    }

    @Override
    public DynamicList<Element> reject(Function<Element, Boolean> rejector) {
        return (DynamicList<Element>) Reject.reject(this, rejector);
    }

    @Override
    public <Transformation> DynamicList<Transformation> transform(Function<Element, Transformation> transformer) {
        return (DynamicList<Transformation>) Transform.transform(this, transformer);
    }

    @Override
    public DynamicList<Element> without(Element... exclusions) {
        return (DynamicList<Element>) Without.without(this, exclusions);
    }

    @Override
    public Partition<Element> partition(Function<Element, Boolean> partitioner) {
        return com.jnape.dynamiccollection.operation.Partition.partition(this, partitioner);
    }

    @Override
    public DynamicList<Element> unique() {
        return (DynamicList<Element>) Unique.unique(this);
    }

    @Override
    public DynamicList<Element> subList(int fromIndex, int toIndex) {
        List<Element> subList = super.subList(fromIndex, toIndex);
        return new DynamicArrayList<Element>(subList);
    }

    @Override
    public DynamicList<DynamicList<Element>> cartesianProduct(List<Element> collection) {
        return CartesianProduct.cartesianProduct(this, collection);
    }

    @Override
    public <Accumulation> Accumulation foldLeft(Accumulation startingAccumulation, HigherOrderFunction<Element, Accumulation> accumulator) {
        return Fold.foldLeft(this, startingAccumulation, accumulator);
    }

    @Override
    public <Accumulation> Accumulation foldRight(Accumulation startingAccumulation, HigherOrderFunction<Element, Accumulation> accumulator) {
        return Fold.foldRight(this, startingAccumulation, accumulator);
    }

    @Override
    public <Comparison extends Comparable<Comparison>> DynamicList<Element> sort(final Function<Element, Comparison> comparator) {
        Comparator<Element> internalComparator = new Comparator<Element>() {
            @Override
            public int compare(Element element1, Element element2) {
                Comparison comparison1 = comparator.apply(element1);
                Comparison comparison2 = comparator.apply(element2);

                return comparison1.compareTo(comparison2);
            }
        };

        DynamicList<Element> sorted = new DynamicArrayList<Element>(this);
        Collections.sort(sorted, internalComparator);
        return sorted;
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public DynamicList<Element> sort() throws ListNotSortableWithoutCustomComparatorException {
        try {
            Collections.sort((List<Comparable>) this);
            return this;
        } catch (ClassCastException notComparable) {
            throw new ListNotSortableWithoutCustomComparatorException(this);
        }
    }

    @Override
    public DynamicList<Element> reverse() {
        Collections.reverse(this);
        return this;
    }

    @Override
    public String join(String combiner) {
        return Join.join(this, combiner);
    }

    @Override
    public Element first() throws ListWasEmptyException {
        return safeGet(0);
    }

    @Override
    public Element last() throws ListWasEmptyException {
        return safeGet(size() - 1);
    }

    @Override
    public Element min(Function<Element, Integer> calculator) {
        return sortByNumericValue(calculator).first();
    }

    @Override
    public Element max(final Function<Element, Integer> calculator) {
        return sortByNumericValue(calculator).last();
    }

    private Element safeGet(int index) {
        if (isEmpty())
            throw new ListWasEmptyException();

        return get(index);
    }

    private DynamicList<Element> sortByNumericValue(final Function<Element, Integer> calculator) {
        Function<Element, Integer> byNumericValue = new Function<Element, Integer>() {
            @Override
            public Integer apply(Element element) {
                return calculator.apply(element);
            }
        };

        return sort(byNumericValue);
    }


}
