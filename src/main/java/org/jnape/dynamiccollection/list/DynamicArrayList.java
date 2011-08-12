package org.jnape.dynamiccollection.list;

import org.jnape.dynamiccollection.datatype.ListPartition;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.lambda.Procedure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;

public class DynamicArrayList<Element> extends ArrayList<Element> implements DynamicList<Element> {

    public DynamicArrayList() {
        super();
    }

    public DynamicArrayList(Collection<? extends Element> elements) {
        super(elements);
    }

    public DynamicArrayList(Element... elements) {
        super(asList(elements));
    }

    @Override
    public DynamicList<Element> subList(int fromIndex, int toIndex) {
        List<Element> subList = super.subList(fromIndex, toIndex);
        return new DynamicArrayList<Element>(subList);
    }

    @Override
    public DynamicList<Element> concat(Collection<Element> collection) {
        DynamicList<Element> combined = new DynamicArrayList<Element>();

        combined.addAll(this);
        combined.addAll(collection);

        return combined;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public DynamicList<DynamicList<Element>> cartesianProduct(Collection<Element> collection) {
        DynamicList<DynamicList<Element>> cartesianProduct = new DynamicArrayList<DynamicList<Element>>();

        for (Element x : this)
            for (Element y : collection)
                cartesianProduct.add(new DynamicArrayList<Element>(x, y));

        return cartesianProduct;
    }

    @Override
    public DynamicList<Element> each(Procedure<Element> procedure) {
        for (Element element : this)
            procedure.execute(element);

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
}
