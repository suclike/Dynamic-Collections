package org.jnape.dynamiccollection.list;

import org.jnape.dynamiccollection.datatype.ListPartition;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.lambda.Procedure;
import org.jnape.dynamiccollection.list.exception.ListNotSortableWithoutCustomComparatorException;
import org.jnape.dynamiccollection.list.exception.ListWasEmptyException;
import org.jnape.dynamiccollection.list.operation.CartesianProduct;
import org.jnape.dynamiccollection.list.operation.Concatenation;
import org.jnape.dynamiccollection.list.operation.Each;

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

    public DynamicArrayList(Collection<? extends Element> elements) {
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
        Concatenation concatenation = operationProvider.concatenation();
        return concatenation.execute(this, collection);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public DynamicList<DynamicList<Element>> cartesianProduct(List<Element> collection) {
        CartesianProduct cartesianProduct = operationProvider.cartesianProduct();
        return cartesianProduct.execute(this, collection);
    }

    @Override
    public DynamicList<Element> each(Procedure<Element> procedure) {
        Each each = operationProvider.each();
        each.iterativelyApply(this, procedure);
        return this;
    }

    @Override
    public DynamicList<Element> collect(Function<Element, Boolean> collector) {
        DynamicList<Element> collected = new DynamicArrayList<Element>();

        for (Element element : this)
            if (collector.apply(element))
                collected.add(element);

        return collected;
    }

    @Override
    public <Transformation> DynamicList<Transformation> transform(Function<Element, Transformation> transformer) {
        DynamicList<Transformation> transformed = new DynamicArrayList<Transformation>();

        for (Element element : this)
            transformed.add(transformer.apply(element));

        return transformed;
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
