package org.jnape.dynamiccollection.list.operation;

import org.jnape.dynamiccollection.list.DynamicArrayList;
import org.jnape.dynamiccollection.list.DynamicList;

import java.util.List;

public class CartesianProduct {

    @SuppressWarnings({"unchecked"})
    public <Element> DynamicList<DynamicList<Element>> execute(List<Element> xs, List<Element> ys) {
        DynamicList<DynamicList<Element>> cartesianProduct = new DynamicArrayList<DynamicList<Element>>();

        for (Element x : xs)
            for (Element y : ys)
                cartesianProduct.add(new DynamicArrayList<Element>(x, y));

        return cartesianProduct;
    }
}
