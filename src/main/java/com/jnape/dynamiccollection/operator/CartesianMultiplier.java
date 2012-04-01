package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.list.DynamicArrayList;
import com.jnape.dynamiccollection.list.DynamicList;

import java.util.List;

public class CartesianMultiplier {

    @SuppressWarnings("unchecked")
    public static <Element> DynamicList<DynamicList<Element>> multiply(List<Element> xs, List<Element> ys) {
        DynamicList<DynamicList<Element>> product = new DynamicArrayList<DynamicList<Element>>();

        for (Element x : xs)
            for (Element y : ys)
                product.add(new DynamicArrayList<Element>(x, y));

        return product;
    }
}
