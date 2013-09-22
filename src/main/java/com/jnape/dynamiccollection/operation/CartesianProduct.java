package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class CartesianProduct {

    @SuppressWarnings("unchecked")
    public static <Element> List<List<Element>> cartesianProduct(List<? extends Element> xs,
                                                                 List<? extends Element> ys) {
        List<List<Element>> product = new ArrayList<List<Element>>();

        for (Element x : xs)
            for (Element y : ys)
                product.add(asList(x, y));

        return product;
    }
}
