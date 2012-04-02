package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.list.DynamicArrayList;
import com.jnape.dynamiccollection.list.DynamicList;

import java.util.List;

public class CartesianProduct {

    @SuppressWarnings("unchecked")
    public static <Element> DynamicList<DynamicList<Element>> cartesianProduct(List<Element> xs, List<Element> ys) {
        DynamicList<DynamicList<Element>> product = new DynamicArrayList<DynamicList<Element>>();

        for (Element x : xs)
            for (Element y : ys)
                product.add(new DynamicArrayList<Element>(x, y));

        return product;
    }
}
