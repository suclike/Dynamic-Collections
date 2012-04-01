package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.HigherOrderFunction;
import com.jnape.dynamiccollection.lambda.Procedure;
import com.jnape.dynamiccollection.list.exception.ListNotSortableWithoutCustomComparatorException;
import com.jnape.dynamiccollection.list.exception.ListWasEmptyException;
import com.jnape.dynamiccollection.operator.*;

import java.util.*;

public class DynamicArrayList<Element> extends ArrayList<Element> implements DynamicList<Element> {

    private final OperationProvider operationProvider;

    DynamicArrayList(OperationProvider operationProvider) {
        this.operationProvider = operationProvider;
    }

    public DynamicArrayList() {
        super();
        operationProvider = new OperationProvider();
    }

    public DynamicArrayList(Collection<Element> elements) {
        super(elements);
        operationProvider = new OperationProvider();
    }

    public DynamicArrayList(Element... elements) {
        super();
        Collections.addAll(this, elements);
        operationProvider = new OperationProvider();
    }

    @Override
    public DynamicList<Element> subList(int fromIndex, int toIndex) {
        List<Element> subList = super.subList(fromIndex, toIndex);
        return new DynamicArrayList<Element>(subList);
    }

    @Override
    public DynamicList<Element> concat(Collection<Element> collection) {
        return (DynamicList<Element>) Concatenator.concatenate(this, collection);
    }

    @Override
    public DynamicList<DynamicCollection<Element>> cartesianProduct(Collection<Element> collection) {
        CartesianMultiplier cartesianMultiplier = operationProvider.cartesianMultiplier();
        return (DynamicList<DynamicCollection<Element>>) cartesianMultiplier.multiply(this, collection);
    }

    @Override
    public DynamicList<Element> each(Procedure<Element> iterativeProcedure) {
        IterativeExecutor iterativeExecutor = operationProvider.iterativeExecutor();
        iterativeExecutor.iterativelyExecute(this, iterativeProcedure);
        return this;
    }

    @Override
    public DynamicList<Element> collect(Function<Element, Boolean> collectionFunction) {
        Collector collector = operationProvider.collector();
        return (DynamicList<Element>) collector.collect(this, collectionFunction);
    }

    @Override
    public DynamicList<Element> reject(Function<Element, Boolean> rejectionFunction) {
        Rejector rejector = operationProvider.rejector();
        return (DynamicList<Element>) rejector.reject(this, rejectionFunction);
    }

    @Override
    public <Transformation> DynamicList<Transformation> transform(Function<Element, Transformation> transformationFunction) {
        Transformer transformer = operationProvider.transformer();
        return (DynamicList<Transformation>) transformer.transform(this, transformationFunction);
    }

    @Override
    public DynamicList<Element> without(Element... exclusions) {
        ElementExcluder elementExcluder = operationProvider.elementExcluder();
        return (DynamicList<Element>) elementExcluder.exclude(this, exclusions);
    }

    @Override
    public Partition<Element> partition(Function<Element, Boolean> partitionFunction) {
        Partitioner partitioner = operationProvider.partitioner();
        return partitioner.partition(this, partitionFunction);
    }

    @Override
    public DynamicList<Element> unique() {
        DynamicList<Element> unique = new DynamicArrayList<Element>();

        for (Element element : this)
            if (!unique.contains(element))
                unique.add(element);

        return unique;
    }

    @Override
    public DynamicList<Element> reverse() {
        Collections.reverse(this);
        return this;
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
    public Element first() throws ListWasEmptyException {
        return safeGet(0);
    }

    @Override
    public Element last() throws ListWasEmptyException {
        return safeGet(size() - 1);
    }

    @Override
    public Element max(final Function<Element, Integer> calculator) {
        return sortByNumericValue(calculator).last();
    }

    @Override
    public Element min(Function<Element, Integer> calculator) {
        return sortByNumericValue(calculator).first();
    }

    @Override
    public String join(String combiner) {
        StringBuilder joined = new StringBuilder();

        for (int i = 0, size = size(); i < size; i++) {
            joined.append(get(i));
            if (i < size - 1)
                joined.append(combiner);
        }

        return joined.toString();
    }

    @Override
    public <Accumulation> Accumulation foldRight(Accumulation startingAccumulation, HigherOrderFunction<Element, Accumulation> accumulator) {
        Folder folder = operationProvider.folder();
        return folder.foldRight(this, startingAccumulation, accumulator);
    }

    @Override
    public <Accumulation> Accumulation foldLeft(Accumulation startingAccumulation, HigherOrderFunction<Element, Accumulation> accumulator) {
        Folder folder = operationProvider.folder();
        return folder.foldLeft(this, startingAccumulation, accumulator);
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
