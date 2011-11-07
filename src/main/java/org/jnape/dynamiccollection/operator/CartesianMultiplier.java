package org.jnape.dynamiccollection.operator;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.list.DynamicArrayList;

import java.util.Collection;

public class CartesianMultiplier {

    @SuppressWarnings({"unchecked"})
    public <Element> DynamicCollection<DynamicCollection<Element>> multiply(Collection<Element> xs, Collection<Element> ys) {
        DynamicCollection<DynamicCollection<Element>> product = new DynamicArrayList<DynamicCollection<Element>>();

        for (Element x : xs)
            for (Element y : ys)
                product.add(new DynamicArrayList<Element>(x, y));

        return product;
    }
}
