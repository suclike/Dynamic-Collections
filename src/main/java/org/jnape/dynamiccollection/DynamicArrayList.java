package org.jnape.dynamiccollection;

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
    public DynamicArrayList<Element> each(Procedure<Element> procedure) {
        for (Element element : this)
            procedure.execute(element);

        return this;
    }

    @Override
    public DynamicArrayList<Element> collect(Function<Element, Boolean> collector) {
        DynamicArrayList<Element> collected = new DynamicArrayList<Element>();

        for (Element element : this)
            if (collector.apply(element))
                collected.add(element);

        return collected;
    }

    @Override
    public <Transformation> DynamicArrayList<Transformation> transform(Function<Element, Transformation> transformer) {
        DynamicArrayList<Transformation> transformed = new DynamicArrayList<Transformation>();

        for (Element element : this)
            transformed.add(transformer.apply(element));

        return transformed;
    }

    @Override
    public DynamicArrayList<Element> without(Element... subtractions) {
        DynamicArrayList<Element> without = new DynamicArrayList<Element>();

        List<Element> subtractionsList = asList(subtractions);

        for (Element element : this)
            if (!subtractionsList.contains(element))
                without.add(element);

        return without;
    }

    @Override
    public DynamicArrayList<Element> unique() {
        DynamicArrayList<Element> unique = new DynamicArrayList<Element>();

        for (Element element : this)
            if (!unique.contains(element))
                unique.add(element);

        return unique;
    }
}
