package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.Procedure;
import com.jnape.dynamiccollection.list.exception.ListWasEmptyException;
import com.jnape.dynamiccollection.operation.*;
import com.jnape.dynamiccollection.operation.Map;

import java.util.*;

public class DynamicArrayList<Element> extends ArrayList<Element> implements DynamicList<Element> {

    public DynamicArrayList() {
        super();
    }

    public DynamicArrayList(Collection<Element> elements) {
        super(elements);
    }

    public DynamicArrayList(Element... elements) {
        this();
        Collections.addAll(this, elements);
    }

    public DynamicArrayList(Iterator<Element> iterator) {
        this();
        while (iterator.hasNext())
            add(iterator.next());
    }

    @Override
    public DynamicList<Element> subList(int fromIndex, int toIndex) {
        List<Element> subList = super.subList(fromIndex, toIndex);
        return new DynamicArrayList<Element>(subList);
    }

    @Override
    public DynamicList<Element> concat(Collection<Element> collection) {
        Collection<Element> concatenation = Concatenate.concatenate(this, collection);
        return new DynamicArrayList<Element>(concatenation);
    }

    @Override
    public DynamicList<Element> each(Procedure<Element> procedure) {
        Each.each(this, procedure);
        return this;
    }

    @Override
    public DynamicList<Element> collect(Function<Element, Boolean> collector) {
        Collection<Element> collected = Collect.collect(this, collector);
        return new DynamicArrayList<Element>(collected);
    }

    @Override
    public DynamicList<Element> reject(Function<Element, Boolean> rejector) {
        Collection<Element> rejected = Reject.reject(this, rejector);
        return new DynamicArrayList<Element>(rejected);
    }

    @Override
    public <Output> DynamicList<Output> map(Function<Element, Output> mapper) {
        Collection<Output> output = Map.map(this, mapper);
        return new DynamicArrayList<Output>(output);
    }

    @Override
    public DynamicList<Element> without(Element... exclusions) {
        Collection<Element> sieved = Without.without(this, exclusions);
        return new DynamicArrayList<Element>(sieved);
    }

    @Override
    public Partition<Element> partition(Function<Element, Boolean> partitioner) {
        return com.jnape.dynamiccollection.operation.Partition.partition(this, partitioner);
    }

    @Override
    public DynamicList<Element> unique() {
        Collection<Element> unique = Unique.unique(this);
        return new DynamicArrayList<Element>(unique);
    }

    @Override
    public DynamicList<DynamicList<Element>> inGroupsOf(int elementsPerGroup) {
        List<List<Element>> groups = InGroupsOf.inGroupsOf(this, elementsPerGroup);
        return graduateToDynamic(groups);
    }

    @Override
    public Boolean any(Function<Element, Boolean> matcher) {
        return Any.any(this, matcher);
    }

    @Override
    public Boolean all(Function<Element, Boolean> matcher) {
        return All.all(this, matcher);
    }

    @Override
    public DynamicList<DynamicList<Element>> cartesianProduct(List<Element> collection) {
        List<List<Element>> product = CartesianProduct.cartesianProduct(this, collection);
        return graduateToDynamic(product);
    }

    @Override
    public <Accumulation> Accumulation foldLeft(Accumulation startingAccumulation, Accumulator<Accumulation, Element> accumulator) {
        return Fold.foldLeft(this, startingAccumulation, accumulator);
    }

    @Override
    public <Accumulation> Accumulation foldRight(Accumulation startingAccumulation, Accumulator<Accumulation, Element> accumulator) {
        return Fold.foldRight(this, startingAccumulation, accumulator);
    }

    @Override
    public Element reduce(Accumulator<Element, Element> accumulator) throws ListWasEmptyException {
        ensureNotEmpty();
        return Reduce.reduce(this, accumulator);
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
    public DynamicList<Element> reverse() {
        List<Element> reversed = Reverse.reverse(this);
        return new DynamicArrayList<Element>(reversed);
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

    private DynamicList<DynamicList<Element>> graduateToDynamic(Collection<List<Element>> nestedCollection) {
        return new DynamicArrayList<List<Element>>(nestedCollection).map(new Function<List<Element>, DynamicList<Element>>() {
            @Override
            public DynamicList<Element> apply(List<Element> elements) {
                return new DynamicArrayList<Element>(elements);
            }
        });
    }

    protected final Element safeGet(int index) {
        ensureNotEmpty();
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

    private void ensureNotEmpty() {
        if (isEmpty())
            throw new ListWasEmptyException();
    }


}
