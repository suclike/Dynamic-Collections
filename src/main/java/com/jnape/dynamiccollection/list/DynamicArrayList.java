package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.IndexedProcedure;
import com.jnape.dynamiccollection.lambda.Procedure;
import com.jnape.dynamiccollection.list.exception.ListWasEmptyException;
import com.jnape.dynamiccollection.operation.*;
import com.jnape.dynamiccollection.operation.Map;

import java.util.*;

public class DynamicArrayList<Element> extends ArrayList<Element> implements DynamicList<Element> {

    public DynamicArrayList() {
        super();
    }

    public DynamicArrayList(Element... elements) {
        this();
        Collections.addAll(this, elements);
    }

    public DynamicArrayList(Iterator<? extends Element> iterator) {
        super();
        while (iterator.hasNext())
            add(iterator.next());
    }

    public DynamicArrayList(Collection<? extends Element> elements) {
        this(elements.iterator());
    }

    @Override
    public DynamicList<Element> subList(int fromIndex, int toIndex) {
        return list(super.subList(fromIndex, toIndex));
    }

    @Override
    public DynamicList<Element> concat(Collection<Element> collection) {
        return list(Concatenate.concatenate(this, collection));
    }

    @Override
    public DynamicList<Element> concat(Element... elements) {
        return concat(list(elements));
    }

    @Override
    public DynamicList<Element> each(IndexedProcedure<? super Element> indexedProcedure) {
        Each.each(this, indexedProcedure);
        return this;
    }

    @Override
    public DynamicList<Element> forEach(Procedure<? super Element> procedure) {
        ForEach.forEach(this, procedure);
        return this;
    }

    @Override
    public DynamicList<Element> filter(Function<? super Element, Boolean> filterer) {
        return list(Filter.filter(this, filterer));
    }

    @Override
    public DynamicList<Element> reject(Function<? super Element, Boolean> rejector) {
        return list(Reject.reject(this, rejector));
    }

    @Override
    public <Output> DynamicList<Output> map(Function<? super Element, Output> mapper) {
        return list(Map.map(this, mapper));
    }

    @Override
    public <Output> DynamicList<Output> mapWhile(Function<? super Element, Output> mapper, Function<? super Output, Boolean> predicate) {
        return list(Map.mapWhile(this, mapper, predicate));
    }

    @Override
    public DynamicList<Element> without(Collection<? super Element> exclusions) {
        return list(Without.without(this, exclusions));
    }

    @Override
    public Partition<Element> partition(Function<? super Element, Boolean> partitioner) {
        return com.jnape.dynamiccollection.operation.Partition.partition(this, partitioner);
    }

    @Override
    public DynamicList<Element> unique() {
        return list(Unique.unique(this));
    }

    @Override
    public DynamicList<Element> unique(Function<? super Element, ?> mapper) {
        return list(Unique.unique(this, mapper));
    }

    @Override
    public DynamicList<DynamicList<Element>> inGroupsOf(int elementsPerGroup) {
        return graduateToDynamic(InGroupsOf.inGroupsOf(this, elementsPerGroup));
    }

    @Override
    public DynamicList<DynamicList<Element>> zip(List<? extends Element>... lists) {
        return graduateToDynamic(Zip.zip(this, lists));
    }

    @Override
    public boolean any(Function<? super Element, Boolean> matcher) {
        return Any.any(this, matcher);
    }

    @Override
    public boolean all(Function<? super Element, Boolean> matcher) {
        return All.all(this, matcher);
    }

    @Override
    public DynamicList<DynamicList<Element>> cartesianProduct(List<? extends Element> collection) {
        return graduateToDynamic(CartesianProduct.cartesianProduct(this, collection));
    }

    @Override
    public DynamicList<DynamicList<Element>> cartesianProduct() {
        return cartesianProduct(this);
    }

    @Override
    public <Accumulation> Accumulation foldLeft(Accumulation startingAccumulation, Accumulator<Accumulation, ? super Element> accumulator) {
        return Fold.foldLeft(this, startingAccumulation, accumulator);
    }

    @Override
    public <Accumulation> Accumulation foldRight(Accumulation startingAccumulation, Accumulator<Accumulation, ? super Element> accumulator) {
        return Fold.foldRight(this, startingAccumulation, accumulator);
    }

    @Override
    public Element reduce(Accumulator<Element, ? super Element> accumulator) throws ListWasEmptyException {
        ensureNotEmpty();
        return Reduce.reduce(this, accumulator);
    }

    @Override
    public <Accumulation> DynamicList<Accumulation> scanLeft(Accumulation startingAccumulation, Accumulator<Accumulation, ? super Element> accumulator) {
        return list(Scan.scanLeft(this, startingAccumulation, accumulator));
    }

    @Override
    public <Comparison extends Comparable<Comparison>> DynamicList<Element> sort(Function<? super Element, Comparison> mapper) {
        return list(Sort.sort(this, mapper));
    }

    @Override
    public DynamicList<Element> reverse() {
        return list(Reverse.reverse(this));
    }

    @Override
    public String join(String combiner) {
        return Join.join(this, combiner);
    }

    @Override
    public Element first() throws ListWasEmptyException {
        return checkedGet(0);
    }

    @Override
    public Element last() throws ListWasEmptyException {
        return checkedGet(size() - 1);
    }

    @Override
    public <Comparison extends Comparable<Comparison>> Element min(Function<? super Element, Comparison> mapper) {
        return sort(mapper).first();
    }

    @Override
    public <Comparison extends Comparable<Comparison>> Element max(Function<? super Element, Comparison> mapper) {
        return sort(mapper).last();
    }

    private DynamicList<DynamicList<Element>> graduateToDynamic(List<List<Element>> nestedCollection) {
        return Fold.foldLeft(nestedCollection, new DynamicArrayList<DynamicList<Element>>(), new Accumulator<DynamicList<DynamicList<Element>>, List<Element>>() {
            @Override
            public DynamicList<DynamicList<Element>> apply(DynamicList<DynamicList<Element>> accumulation, List<Element> elements) {
                accumulation.add(list(elements));
                return accumulation;
            }
        });
    }

    protected final Element checkedGet(int index) {
        ensureNotEmpty();
        return get(index);
    }

    private void ensureNotEmpty() {
        if (isEmpty())
            throw new ListWasEmptyException();
    }

    public static <Element> DynamicArrayList<Element> list() {
        return new DynamicArrayList<Element>();
    }

    public static <Element> DynamicArrayList<Element> list(Element... elements) {
        return new DynamicArrayList<Element>(elements);
    }

    public static <Element> DynamicArrayList<Element> list(Collection<Element> collection) {
        return new DynamicArrayList<Element>(collection);
    }

    public static <Element> DynamicArrayList<Element> list(Iterator<Element> iterator) {
        return new DynamicArrayList<Element>(iterator);
    }
}
