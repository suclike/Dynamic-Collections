package org.jnape.dynamiccollection.list;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.datatype.ListPartition;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.lambda.Procedure;
import org.jnape.dynamiccollection.list.exception.ListNotSortableWithoutCustomComparatorException;
import org.jnape.dynamiccollection.list.exception.ListWasEmptyException;
import org.jnape.dynamiccollection.operator.CartesianMultiplier;
import org.jnape.dynamiccollection.operator.*;
import org.jnape.dynamiccollection.operator.Concatenator;

import java.util.*;

import static java.util.Arrays.asList;

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
        this(asList(elements));
    }

    @Override
    public DynamicList<Element> subList(int fromIndex, int toIndex) {
        List<Element> subList = super.subList(fromIndex, toIndex);
        return new DynamicArrayList<Element>(subList);
    }

    @Override
    public DynamicList<Element> concat(Collection<Element> collection) {
        Concatenator concatenator = operationProvider.concatenator();
        return (DynamicList<Element>) concatenator.concatenate(this, collection);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public DynamicList<DynamicCollection<Element>> cartesianProduct(Collection<Element> collection) {
        CartesianMultiplier cartesianMultiplier = operationProvider.cartesianMultiplier();
        return (DynamicList<DynamicCollection<Element>>) cartesianMultiplier.multiply(this, collection);
    }

    @Override
    public DynamicList<Element> each(Procedure<Element> procedure) {
        IterativeExecutor iterativeExecutor = operationProvider.iterativeExecutor();
        iterativeExecutor.iterativelyExecute(this, procedure);
        return this;
    }

    @Override
    public DynamicList<Element> collect(Function<Element, Boolean> sieve) {
        Collector collector = operationProvider.collector();
        return (DynamicList<Element>) collector.collect(this, sieve);
    }

    @Override
    public <Transformation> DynamicList<Transformation> transform(Function<Element, Transformation> function) {
        Transformer transformer = operationProvider.transformer();
        return (DynamicList<Transformation>) transformer.transform(this, function);
    }

    @Override
    public DynamicList<Element> without(Element... subtractions) {
        DynamicList<Element> without = new DynamicArrayList<Element>();

        List<Element> subtractionsList = asList(subtractions);

        for (Element element : this)
            if (!subtractionsList.contains(element))
                without.add(element);

        return without;
    }

    @Override
    public ListPartition<Element> partition(Function<Element, Boolean> sieve) {
        List<Element> trues = new DynamicArrayList<Element>();
        List<Element> falses = new DynamicArrayList<Element>();

        for (Element element : this)
            (sieve.apply(element) ? trues : falses).add(element);

        return new ListPartition<Element>(trues, falses);
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

        Collections.sort(this, internalComparator);
        return this;
    }

    @Override
    public Element first() throws ListWasEmptyException {
        ensureNotEmpty();
        return get(0);
    }

    @Override
    public Element last() throws ListWasEmptyException {
        ensureNotEmpty();
        return get(size() - 1);
    }

    @Override
    public Element max(final Function<Element, Integer> calculator) {
        return sortByNumericValue(calculator).last();
    }

    @Override
    public Element min(Function<Element, Integer> calculator) {
        return sortByNumericValue(calculator).first();
    }

    private void ensureNotEmpty() {
        if (isEmpty())
            throw new ListWasEmptyException();
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
