package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.datatype.option.Option;
import com.jnape.dynamiccollection.datatype.tuple.Tuple2;
import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;
import com.jnape.dynamiccollection.lambda.dyadic.DyadicFunction;
import com.jnape.dynamiccollection.lambda.dyadic.IndexedProcedure;
import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import com.jnape.dynamiccollection.lambda.monadic.MonadicProcedure;
import com.jnape.dynamiccollection.list.exception.ListWasEmptyException;
import com.jnape.dynamiccollection.operation.*;
import com.jnape.dynamiccollection.operation.Map;

import java.util.*;


public class DynamicArrayList<Element> extends ArrayList<Element> implements DynamicList<Element> {

    public DynamicArrayList() {
        super();
    }

    public DynamicArrayList(Element[] elements) {
        this();
        Collections.addAll(this, elements);
    }

    public DynamicArrayList(Element element, Element... elements) {
        this();
        add(element);
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
    public Element get(int i) {
        return super.get(i < 0 ? size() + i : i);
    }

    @Override
    public DynamicArrayList<Element> subList(int fromIndex, int toIndex) {
        return new DynamicArrayList<Element>(super.subList(fromIndex, toIndex));
    }

    @Override
    public DynamicArrayList<Element> concat(Collection<Element> collection) {
        return new DynamicArrayList<Element>(Concatenate.concatenate(this, collection));
    }

    @Override
    public DynamicList<Element> concat(Element... elements) {
        return concat(new DynamicArrayList<Element>(elements));
    }

    @Override
    public Option<Element> find(MonadicFunction<? super Element, Boolean> predicate) {
        return Find.find(predicate, this);
    }

    @Override
    public DynamicList<Element> each(IndexedProcedure<? super Element> indexedProcedure) {
        Each.each(this, indexedProcedure);
        return this;
    }

    @Override
    public DynamicList<Element> forEach(MonadicProcedure<? super Element> procedure) {
        ForEach.forEach(this, procedure);
        return this;
    }

    @Override
    public DynamicList<Element> filter(MonadicFunction<? super Element, Boolean> filterer) {
        return new DynamicArrayList<Element>(Filter.filter(this, filterer));
    }

    @Override
    public DynamicList<Element> reject(MonadicFunction<? super Element, Boolean> rejector) {
        return new DynamicArrayList<Element>(Reject.reject(this, rejector));
    }

    @Override
    public <Output> DynamicList<Output> map(MonadicFunction<? super Element, Output> mapper) {
        return new DynamicArrayList<Output>(Map.map(this, mapper));
    }

    @Override
    public <Output> DynamicList<Output> map(DyadicFunction<Number, ? super Element, Output> mapper) {
        return new DynamicArrayList<Output>(Map.map(this, mapper));
    }

    @Override
    public <Output> DynamicList<Output> mapWhile(MonadicFunction<? super Element, Output> mapper,
                                                 MonadicFunction<? super Output, Boolean> predicate) {
        return new DynamicArrayList<Output>(Map.mapWhile(this, mapper, predicate));
    }

    @Override
    public DynamicList<Element> without(Collection<? super Element> exclusions) {
        return new DynamicArrayList<Element>(Without.without(this, exclusions));
    }

    @Override
    public Partition<Element> partition(MonadicFunction<? super Element, Boolean> partitioner) {
        return com.jnape.dynamiccollection.operation.Partition.partition(this, partitioner);
    }

    @Override
    public DynamicList<Element> unique() {
        return new DynamicArrayList<Element>(Unique.unique(this));
    }

    @Override
    public DynamicList<Element> unique(MonadicFunction<? super Element, ?> mapper) {
        return new DynamicArrayList<Element>(Unique.unique(this, mapper));
    }

    @Override
    public DynamicList<Element> duplicates() {
        return new DynamicArrayList<Element>(Duplicates.duplicates(this));
    }

    @Override
    public DynamicList<DynamicList<Element>> group() {
        return graduateToDynamic(Group.group(this));
    }

    @Override
    public DynamicList<DynamicList<Element>> group(MonadicFunction<? super Element, ?> mapper) {
        return graduateToDynamic(Group.group(this, mapper));
    }

    @Override
    public DynamicList<DynamicList<Element>> inGroupsOf(int elementsPerGroup) {
        return graduateToDynamic(InGroupsOf.inGroupsOf(this, elementsPerGroup));
    }

    @Override
    public DynamicList<DynamicList<Element>> allSequencesOf(int elementsPerSequences) {
        return graduateToDynamic(AllSequencesOf.allSequencesOf(elementsPerSequences, this));
    }

    @Override
    public DynamicList<DynamicList<Element>> zip(List<? extends Element>... lists) {
        return graduateToDynamic(Zip.zip(this, lists));
    }

    @Override
    public <Output> DynamicList<Output> zipWith(MonadicFunction<Tuple2<Element, Element>, Output> zipper,
                                                List<? extends Element> list) {
        return new DynamicArrayList<Output>(Zip.zipWith(zipper, this, list));
    }

    @Override
    public boolean any(MonadicFunction<? super Element, Boolean> matcher) {
        return Any.any(this, matcher);
    }

    @Override
    public boolean anyWhile(MonadicFunction<? super Element, Boolean> matcher,
                            MonadicFunction<? super Element, Boolean> predicate) {
        return Any.anyWhile(this, matcher, predicate);
    }

    @Override
    public boolean all(MonadicFunction<? super Element, Boolean> matcher) {
        return All.all(this, matcher);
    }

    @Override
    public boolean none(MonadicFunction<? super Element, Boolean> matcher) {
        return None.none(this, matcher);
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
    public DynamicList<Element> shuffle() {
        return new DynamicArrayList<Element>(Shuffle.shuffle(this));
    }

    @Override
    public <Accumulation> Accumulation foldLeft(Accumulation startingAccumulation,
                                                Accumulator<Accumulation, ? super Element> accumulator) {
        return Fold.foldLeft(this, startingAccumulation, accumulator);
    }

    @Override
    public <Accumulation> Accumulation foldRight(Accumulation startingAccumulation,
                                                 Accumulator<Accumulation, ? super Element> accumulator) {
        return Fold.foldRight(this, startingAccumulation, accumulator);
    }

    @Override
    public Element reduce(Accumulator<Element, ? super Element> accumulator) throws ListWasEmptyException {
        ensureNotEmpty();
        return Reduce.reduce(this, accumulator);
    }

    @Override
    public <Accumulation> DynamicList<Accumulation> scanLeft(Accumulation startingAccumulation,
                                                             Accumulator<Accumulation, ? super Element> accumulator) {
        return new DynamicArrayList<Accumulation>(Scan.scanLeft(this, startingAccumulation, accumulator));
    }

    @Override
    public DynamicList<Element> scanLeft(Accumulator<Element, ? super Element> accumulator) {
        return new DynamicArrayList<Element>(Scan.scanLeft(this, accumulator));
    }

    @Override
    public <Comparison extends Comparable<Comparison>> DynamicList<Element> sort(
            MonadicFunction<? super Element, Comparison> mapper) {
        return new DynamicArrayList<Element>(Sort.sort(this, mapper));
    }

    @Override
    public DynamicList<Element> reverse() {
        return new DynamicArrayList<Element>(Reverse.reverse(this));
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
    public <Comparison extends Comparable<Comparison>> Element min(
            MonadicFunction<? super Element, Comparison> mapper) {
        return sort(mapper).first();
    }

    @Override
    public <Comparison extends Comparable<Comparison>> Element max(
            MonadicFunction<? super Element, Comparison> mapper) {
        return sort(mapper).last();
    }

    private DynamicList<DynamicList<Element>> graduateToDynamic(List<List<Element>> nestedCollection) {
        return Fold.foldLeft(nestedCollection, new DynamicArrayList<DynamicList<Element>>(), new Accumulator<DynamicList<DynamicList<Element>>, List<Element>>() {
            @Override
            public DynamicList<DynamicList<Element>> apply(DynamicList<DynamicList<Element>> accumulation,
                                                           List<Element> elements) {
                accumulation.add(new DynamicArrayList<Element>(elements));
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
}
