package org.jnape.dynamiccollection;

import org.jnape.dynamiccollection.lambda.Function;

import java.util.ArrayList;
import java.util.Collection;

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

    public <Transformation> DynamicArrayList<Transformation> transform(Function<Element, Transformation> transformer) {
        DynamicArrayList<Transformation> transformed = new DynamicArrayList<Transformation>();

        for (Element element : this)
            transformed.add(transformer.apply(element));

        return transformed;
    }
}
