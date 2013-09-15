package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.swap;

public class Permute {

    public static <Element> List<List<Element>> permutations(List<Element> elements) {
        List<List<Element>> result = new ArrayList<List<Element>>();
        permute(new ArrayList<Element>(elements), 0, result);
        return result;
    }

    private static <Element> void permute(List<Element> elements, int k, List<List<Element>> result) {
        for (int i = k; i < elements.size(); i++) {
            swap(elements, i, k);
            permute(elements, k + 1, result);
            swap(elements, k, i);
        }
        if (k == elements.size() - 1)
            result.add(new ArrayList<Element>(elements));
    }
}
