package com.jnape.dynamiccollection.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort {

    public static <Element extends Comparable<Element>> List<Element> sort(List<Element> elements) {
        List<Element> sorted = new ArrayList<Element>(elements);
        Collections.sort(sorted);
        return sorted;
    }
}
